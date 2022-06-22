package com.api.dcms.service;

import com.api.dcms.model.entity.Convenio;
import com.api.dcms.model.repository.ConvenioRepository;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.List;
=======
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
>>>>>>> code-pk

@Service
public class ConvenioService {

    private ConvenioRepository repository;

<<<<<<< HEAD
    public ConvenioService (ConvenioRepository repository){
        this.repository = repository;
    }

    public List<Convenio> getCargo(){
        return repository.findAll();
=======
    public ConvenioService(ConvenioRepository repository){
        this.repository = repository;
    }

    public List<Convenio> getConvenios() {return repository.findAll();}

    @Transactional
    public Convenio salvar(Convenio convenio) {
        return repository.save(convenio);
    }

    public Optional<Convenio> getConvenioById(Long idConvenio) {
        return repository.findById(idConvenio);
>>>>>>> code-pk
    }
}
