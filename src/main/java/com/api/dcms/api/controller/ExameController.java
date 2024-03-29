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

import com.api.dcms.api.dto.ExameDTO;
import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.Convenio;
import com.api.dcms.model.entity.Exame;
import com.api.dcms.model.entity.Medico;
import com.api.dcms.model.entity.Paciente;
import com.api.dcms.service.ConvenioService;
import com.api.dcms.service.ExameService;
import com.api.dcms.service.MedicoService;
import com.api.dcms.service.PacienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/exame")
@RequiredArgsConstructor
public class ExameController {

    private final ExameService service;
    private final PacienteService pacienteService;
    private final ConvenioService convenioService;
    private final MedicoService medicoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Exame> exame = service.getExame();
        return ResponseEntity.ok(exame.stream().map(ExameDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um exame")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Exame ok"),
            @ApiResponse(code = 404, message = "Exame não encontrado")
    })
    public ResponseEntity get(@PathVariable("id") @ApiParam("ID do exame") Long id) {
        Optional<Exame> exame = service.getExameById(id);
        if (!exame.isPresent()) {
            return new ResponseEntity("Exame não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(exame.map(ExameDTO::create));
    }



    @PostMapping()
    @ApiOperation("Salva um novo exame")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Exame salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao salvar o exame")
    })
    public ResponseEntity post(ExameDTO dto) {
        try {
            Exame exame = converter(dto);
            exame = service.salvar(exame);
            return new ResponseEntity(exame, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ApiOperation("Alterar detalhes do exame")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Alterações salvas com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao alterar o exame")
    })
    public ResponseEntity atualizar(@PathVariable("id") Long id, ExameDTO dto) {
        if (!service.getExameById(id).isPresent()) {
            return new ResponseEntity("Exame não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Exame exame = converter(dto);
            exame.setIdExame(id);
            service.salvar(exame);
            return ResponseEntity.ok(exame);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation("Deletar o exame")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Exame deletado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao deletar o exame")
    })
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Exame> exame = service.getExameById(id);
        if (!exame.isPresent()) {
            return new ResponseEntity("Exame não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(exame.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Exame converter(ExameDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Exame exame = modelMapper.map(dto, Exame.class);
        if (dto.getIdPaciente() != null) {
            Optional<Paciente> paciente = pacienteService.getPacienteById(dto.getIdPaciente());
            if (!paciente.isPresent()) {
                exame.setPaciente(null);
            } else {
                exame.setPaciente(paciente.get());
            }
        }
        if (dto.getIdConvenio() != null) {
            Optional<Convenio> convenio = convenioService.getConvenioById(dto.getIdConvenio());
            if (!convenio.isPresent()) {
                exame.setConvenio(null);
            } else {
                exame.setConvenio(convenio.get());
            }
        }
        if (dto.getIdMedico() != null) {
            Optional<Medico> medico = medicoService.getMedicoById(dto.getIdMedico());
            if (!medico.isPresent()) {
                exame.setMedico(null);
            } else {
                exame.setMedico(medico.get());
            }
        }
        return exame;
    }
}