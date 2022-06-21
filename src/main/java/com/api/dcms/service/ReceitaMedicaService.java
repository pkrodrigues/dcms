package com.api.dcms.service;

import com.api.dcms.model.entity.ReceitaMedica;
import com.api.dcms.model.repository.ReceitaMedicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaMedicaService {

    private ReceitaMedicaRepository repository;

    public ReceitaMedicaService (ReceitaMedicaRepository repository) {
        this.repository = repository;
    }

    public List<ReceitaMedica> getReceitaMedica() {
        return repository.findAll();
    }
}
