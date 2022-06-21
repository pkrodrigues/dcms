package com.api.dcms.service;

import com.api.dcms.model.entity.Convenio;
import com.api.dcms.model.repository.ConvenioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenioService {

    private ConvenioRepository repository;

    public ConvenioService (ConvenioRepository repository){
        this.repository = repository;
    }

    public List<Convenio> getCargo(){
        return repository.findAll();
    }
}
