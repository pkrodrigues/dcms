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

import com.api.dcms.api.dto.ConvenioDTO;
import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.Convenio;
import com.api.dcms.service.ConvenioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/convenios")

public class ConvenioController {
    private final ConvenioService service;
    
    private Convenio converter(ConvenioDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Convenio convenio = modelMapper.map(dto, Convenio.class);
        return convenio;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Convenio> convenios =service.getConvenios();
        return ResponseEntity.ok(convenios.stream().map(ConvenioDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Convenio> convenio = service.getConvenioById(id);
        if (!convenio.isPresent()) {
            return new ResponseEntity("Convenio não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(convenio.map(ConvenioDTO::create));
    }

    @PostMapping
    public ResponseEntity post(ConvenioDTO dto) {
        try {
            Convenio convenio = converter(dto);
            convenio = service.salvar(convenio);
            return new ResponseEntity(convenio, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, ConvenioDTO dto) {
        if (!service.getConvenioById(id).isPresent()) {
            return new ResponseEntity("Convenio não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Convenio convenio = converter(dto);
            convenio.setIdConvenio(id);
            service.salvar(convenio);
            return ResponseEntity.ok(convenio);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Convenio> convenio = service.getConvenioById(id);
        if (!convenio.isPresent()) {
            return new ResponseEntity("Convenio não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(convenio.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

