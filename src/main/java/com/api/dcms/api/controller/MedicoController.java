package com.api.dcms.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

import com.api.dcms.api.dto.MedicoDTO;
import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.Medico;
import com.api.dcms.service.ConvenioService;
import com.api.dcms.service.MedicoService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/v1/medico")
@RequiredArgsConstructor

public class MedicoController {

    private final MedicoService service;
    private final ConvenioService convenioService;

    private Medico converter(MedicoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Medico medico = modelMapper.map(dto, Medico.class);

        return medico;
    }


    @GetMapping()
    public ResponseEntity get() {
        List<Medico> medico = service.getMedico();
        return ResponseEntity.ok(medico.stream().map(MedicoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um médico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Médico ok"),
            @ApiResponse(code = 404, message = "Médico não encontrado")
    })
    public ResponseEntity get(@PathVariable("id") @ApiParam("ID do médico") Long id) {
        Optional<Medico> medico = service.getMedicoById(id);
        if (!medico.isPresent()) {
            return new ResponseEntity("Medico não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(medico.map(MedicoDTO::create));
    }

    @PostMapping()
    @ApiOperation("Salva um novo Médico")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Médico salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao salvar o médico")
    })
    public ResponseEntity post(MedicoDTO dto) {
        try {
            Medico medico = converter(dto);
            medico = service.salvar(medico);
            return new ResponseEntity(medico, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ApiOperation("Alterar detalhes")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Alterações salvas com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao alterar")
    })
    public ResponseEntity atualizar(@PathVariable("id") Long id, MedicoDTO dto) {
        if (!service.getMedicoById(id).isPresent()) {
            return new ResponseEntity("Medico não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Medico medico = converter(dto);
            medico.setIdMedico(id);
            service.salvar(medico);
            return ResponseEntity.ok(medico);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation("Deletar")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Deletado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao deletar")
    })
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Medico> medico = service.getMedicoById(id);
        if (!medico.isPresent()) {
            return new ResponseEntity("Medico não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(medico.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}