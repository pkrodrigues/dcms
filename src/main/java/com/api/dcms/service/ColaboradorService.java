package com.api.dcms.service;

import com.api.dcms.model.entity.Colaborador;
import com.api.dcms.model.repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    private ColaboradorRepository repository;

    public ColaboradorService (ColaboradorRepository repository){
        this.repository = repository;
    }

    public List<Colaborador> getCargo() {
        return repository.findAll();
    }

    public Optional<Colaborador> getColaboradorById(Long id) {
        return repository.findById(id);
    }
}
