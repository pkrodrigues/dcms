package com.api.dcms.service;

import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.Medico;
import com.api.dcms.model.repository.MedicoRepository;

import io.netty.util.collection.LongObjectHashMap;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class MedicoService {

    private MedicoRepository repository;

    public MedicoService (MedicoRepository repository) {
        this.repository = repository;
    }

    public List<Medico> getMedico() {
        return repository.findAll();
    }

    public Optional<Medico> getMedicoById(Long idMedico) {
        return repository.findById(idMedico);
    }

    public void validar(Medico medico) {
        if (medico.getNome() == null || medico.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido"+medico.getNome()+" "+medico.getTelefone());
        }
    }

    @Transactional
    public Medico salvar(Medico medico) {
        validar(medico);
        return repository.save(medico);
    }
    @Transactional
    public void excluir(Medico medico) {
        Objects.requireNonNull(medico.getIdMedico());
        repository.delete(medico);
    }
}
