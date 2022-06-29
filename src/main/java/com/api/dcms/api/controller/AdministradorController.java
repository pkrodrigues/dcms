package com.api.dcms.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dcms.api.dto.AdministradorDTO;
import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.Cargo;
import com.api.dcms.model.entity.Administrator;
import com.api.dcms.service.AdministradorService;
import com.api.dcms.service.CargoService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/administrador")
@RequiredArgsConstructor

public class AdministradorController {

    private final AdministradorService service;
    private final CargoService cargoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Administrator> funcionario = service.getAdministrador();
        return ResponseEntity.ok(funcionario.stream().map(AdministradorDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Administrator> funcionario = service.getAdministradorById(id);
        if (!funcionario.isPresent()) {
            return new ResponseEntity("Funcionário não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(funcionario.map(AdministradorDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(AdministradorDTO dto) {
        try {
            Administrator funcionario = converter(dto);
            funcionario = service.salvar(funcionario);
            return new ResponseEntity(funcionario, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, AdministradorDTO dto) {
        if (!service.getAdministradorById(id).isPresent()) {
            return new ResponseEntity("Funcionário não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Administrator funcionario = converter(dto);
            funcionario.setIdAdministrador(id);
            service.salvar(funcionario);
            return ResponseEntity.ok(funcionario);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Administrator> funcionario = service.getAdministradorById(id);
        if (!funcionario.isPresent()) {
            return new ResponseEntity("Funcionário não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(funcionario.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Administrator converter(AdministradorDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Administrator funcionario = modelMapper.map(dto, Administrator.class);
        if (dto.getIdAdministrador() != null) {
            Optional<Cargo> cargo = cargoService.getCargoById(dto.getIdAdministrador());
            if (!cargo.isPresent()) {
                funcionario.setCargo(null);
            } else {
                funcionario.setCargo(cargo.get());
            }
        }
        return funcionario;
    }
}