package com.api.dcms.service;

import com.api.dcms.model.entity.Medico;
import com.api.dcms.model.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private MedicoRepository repository;

    public MedicoService (MedicoRepository repository) {
        this.repository = repository;
    }

    public List<Medico> getMedico() {
        return repository.findAll();
    }
}
