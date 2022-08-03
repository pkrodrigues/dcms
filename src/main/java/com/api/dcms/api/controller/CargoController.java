package com.api.dcms.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.*;
import com.api.dcms.api.dto.FuncionarioDTO;
import com.api.dcms.api.dto.CargoDTO;
import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.Funcionario;
import com.api.dcms.model.entity.Cargo;
import com.api.dcms.service.FuncionarioService;
import com.api.dcms.service.CargoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cargo")
@RequiredArgsConstructor

public class CargoController {

    private final CargoService service;
    private final FuncionarioService funcionarioService;


    @GetMapping()
    public ResponseEntity get() {
        List<Cargo> cargo = service.getCargo();
        return ResponseEntity.ok(cargo.stream().map(CargoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um cargo")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cargo ok"),
            @ApiResponse(code = 404, message = "Cargo não encontrado")
    })
    public ResponseEntity get(@PathVariable("id") @ApiParam("ID do cargo") Long id) {
        Optional<Cargo> cargo = service.getCargoById(id);
        if (!cargo.isPresent()) {
            return new ResponseEntity("Cargo não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cargo.map(CargoDTO::create));
    }

    @GetMapping("{id}/funcionario")
    public ResponseEntity getFuncionario(@PathVariable("id") @ApiParam("ID do funcionário") Long id) {
        Optional<Cargo> cargo = service.getCargoById(id);
        if (!cargo.isPresent()) {
            return new ResponseEntity("Cargo não encontrado", HttpStatus.NOT_FOUND);
        }
        List<Funcionario> funcionario = funcionarioService.getFuncionarioByCargo(cargo);
        return ResponseEntity.ok(funcionario.stream().map(FuncionarioDTO::create).collect(Collectors.toList()));
    }
    @PostMapping()
    @ApiOperation("Salva um novo cargo")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cargo salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao salvar o cargo")
    })
    public ResponseEntity post(CargoDTO dto) {
        try {
            Cargo cargo = converter(dto);
            cargo = service.salvar(cargo);
            return new ResponseEntity(cargo, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping()
    @ApiOperation("Alterar detalhes do cargo")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Alterações salvas com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao alterar o cargo")
    })
    public ResponseEntity atualizar(@PathVariable("id") Long id, CargoDTO dto) {
        if (!service.getCargoById(id).isPresent()) {
            return new ResponseEntity("Cargo não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Cargo cargo= converter(dto);
            cargo.setIdCargo(id);
            service.salvar(cargo);
            return ResponseEntity.ok(cargo);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping()
    @ApiOperation("Deletar o cargo")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cargo deletado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao deletar o cargo")
    })
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Cargo> cargo = service.getCargoById(id);
        if (!cargo.isPresent()) {
            return new ResponseEntity("Cargo não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(cargo.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Cargo converter(CargoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Cargo.class);
    }
}