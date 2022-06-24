package com.api.dcms.api.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}

