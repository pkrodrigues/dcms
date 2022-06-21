package com.api.dcms.service;

import com.api.dcms.model.entity.Exame;
import com.api.dcms.model.repository.ExameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExameService {

    private ExameRepository repository;

    public ExameService (ExameRepository repository){
        this.repository = repository;
    }

    public List<Exame> getCargo() {
        return repository.findAll();
    }
}
