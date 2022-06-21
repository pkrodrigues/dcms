package com.api.dcms.service;

import com.api.dcms.model.entity.Consulta;
import com.api.dcms.model.repository.ConsultaRepository;
import com.sun.xml.bind.v2.schemagen.xmlschema.List;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    private ConsultaRepository repository;

    public ConsultaService (ConsultaRepository repository){
        this.repository = repository;
    }

    public List<Consulta> getConsulta() {
        return repository.findAll();
    }
}
