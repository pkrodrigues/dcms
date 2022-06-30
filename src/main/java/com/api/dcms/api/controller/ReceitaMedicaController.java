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

import com.api.dcms.api.dto.PacienteDTO;
import com.api.dcms.api.dto.ReceitaMedicaDTO;
import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.Medico;
import com.api.dcms.model.entity.Paciente;
import com.api.dcms.model.entity.ReceitaMedica;
import com.api.dcms.service.MedicoService;
import com.api.dcms.service.PacienteService;
import com.api.dcms.service.ReceitaMedicaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/receita")
@RequiredArgsConstructor
public class ReceitaMedicaController {

    private final ReceitaMedicaService service;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;


    @GetMapping()
    public ResponseEntity get() {
        List<ReceitaMedica> receitaMedica = service.getReceitaMedica();
        return ResponseEntity.ok(receitaMedica.stream().map(ReceitaMedicaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<ReceitaMedica> receitaMedica = service.getReceitaMedicaById(id);
        if (!receitaMedica.isPresent()) {
            return new ResponseEntity("Exame não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(receitaMedica.map(ReceitaMedicaDTO::create));
    }

    @GetMapping("{id}/Paciente")
    public ResponseEntity getMedico(@PathVariable("id") Long id) {
        Optional<ReceitaMedica> receitaMedica = service.getReceitaMedicaById(id);
        if (!receitaMedica.isPresent()) {
            return new ResponseEntity("Receita não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(receitaMedica.get().getPaciente().stream().map(PacienteDTO::create).collect(Collectors.toList()));
    }

    @PostMapping()
    public ResponseEntity post(ReceitaMedicaDTO dto) {
        try {
            ReceitaMedica receitaMedica = converter(dto);
            receitaMedica = service.salvar(receitaMedica);
            return new ResponseEntity(receitaMedica, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, ReceitaMedicaDTO dto) {
        if (!service.getReceitaMedicaById(id).isPresent()) {
            return new ResponseEntity("Receita não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            ReceitaMedica receitaMedica = converter(dto);
            receitaMedica.setIdReceitaMed(id);
            service.salvar(receitaMedica);
            return ResponseEntity.ok(receitaMedica);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<ReceitaMedica> receitaMedica = service.getReceitaMedicaById(id);
        if (!receitaMedica.isPresent()) {
            return new ResponseEntity("Receita não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(receitaMedica.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ReceitaMedica converter(ReceitaMedicaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        ReceitaMedica receitaMedica = modelMapper.map(dto, ReceitaMedica.class);
        if (dto.getIdPaciente() != null) {
            Optional<Paciente> paciente = pacienteService.getPacienteById(dto.getIdPaciente());
            if (!paciente.isPresent()) {
                receitaMedica.setPaciente(null);
            } else {
                receitaMedica.setPaciente(paciente.get());
            }
        }
        if (dto.getIdMedico() != null) {
            Optional<Medico> medico = medicoService.getMedicoById(dto.getIdMedico());
            if (!medico.isPresent()) {
                receitaMedica.setMedico(null);
            } else {
                receitaMedica.setMedico(medico.get());
            }
        }
        return receitaMedica;
    }
}


