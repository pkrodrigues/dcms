package com.api.dcms.service;

import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.*;
import com.api.dcms.model.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PacienteService {

    private PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public List<Paciente> getPacientes() {
        return repository.findAll();
    }
    
    public Optional<Paciente> getPacienteById(Long id) {
        return repository.findById(id);
    }

    public void validar(Paciente paciente) {
        if (paciente.getNome() == null || paciente.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido"+paciente.getNome()+" "+paciente.getTelefone());
        }
    }

    @Transactional
    public Paciente salvar(Paciente paciente) {
        validar(paciente);
        return repository.save(paciente);
    }
    @Transactional
    public void excluir(Paciente paciente) {
        Objects.requireNonNull(paciente.getId());
        repository.delete(paciente);
    }
}
