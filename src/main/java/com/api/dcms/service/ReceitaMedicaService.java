package com.api.dcms.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.ReceitaMedica;
import com.api.dcms.model.repository.ReceitaMedicaRepository;

@Service

public class ReceitaMedicaService {
    private ReceitaMedicaRepository repository;

    public ReceitaMedicaService (ReceitaMedicaRepository repository) {
        this.repository = repository;
    }

    public List<ReceitaMedica> getReceitaMedica() {
        return repository.findAll();
    }

    public Optional<ReceitaMedica> getReceitaMedicaById(Long id) {
        return repository.findById(id);
    }
    

    @Transactional
    public ReceitaMedica salvar(ReceitaMedica receitaMedica) {
        validar(receitaMedica);
        return repository.save(receitaMedica);
    }

    @Transactional
    public void excluir(ReceitaMedica receitaMedica) {
        Objects.requireNonNull(receitaMedica.getIdReceitaMed());
        repository.delete(receitaMedica);
    }

    public void validar(ReceitaMedica receitaMedica) {

        if (receitaMedica.getDtEmissaoReceita() == null) {
            throw new RegraNegocioException("A Data de emissão é inválida");
        }
        if (receitaMedica.getPrescricao() == null) {
            throw new RegraNegocioException("o preenchimento da prescrição Médica não pode estar vazio");
        }
        if (receitaMedica.getPaciente() == null || receitaMedica.getPaciente().equals("")|| receitaMedica.getPaciente().getIdPaciente() == null) {
            throw new RegraNegocioException("Paciênte não encontrado");
        }
        if (receitaMedica.getMedico() == null || receitaMedica.getMedico().equals("")|| receitaMedica.getMedico().getIdMedico() == null) {
            throw new RegraNegocioException("Médico não encontrado");
        }
    }
    }


