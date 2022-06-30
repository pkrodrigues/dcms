package com.api.dcms.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.Exame;

import com.api.dcms.model.repository.ExameRepository;

@Service

public class ExameService {
    private ExameRepository repository;

    public ExameService(ExameRepository repository) {
        this.repository = repository;
    }

    public List<Exame> getExame() {
        return repository.findAll();
    }

    public Optional<Exame> getExameById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Exame salvar(Exame exame) {
        validar(exame);
        return repository.save(exame);
    }

    @Transactional
    public void excluir(Exame exame) {
        Objects.requireNonNull(exame.getIdExame());
        repository.delete(exame);
    }
    
    public void validar(Exame exame) {

        if (exame.getData() == null) {
            throw new RegraNegocioException("Data inválido");
        }
        if (exame.getHorario() == null) {
            throw new RegraNegocioException("Hora inválido");
        }
        if (exame.getConvenio().getIdConvenio() == null || exame.getConvenio().getIdConvenio() == 0) {
            throw new RegraNegocioException("Convenio inválido");
        }
        if (exame.getPaciente().getNome() == null || exame.getPaciente().getNome().trim().equals("")) {
            throw new RegraNegocioException("Paciênte não encontrado");
        }
        if (exame.getMedico().getNome() == null || exame.getMedico().getNome().trim().equals("")) {
            throw new RegraNegocioException("Médico não encontrado");
        }
    }
}


