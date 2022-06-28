package com.api.dcms.service;

import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.Convenio;
import com.api.dcms.model.repository.ConvenioRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConvenioService {

    private ConvenioRepository repository;

    public ConvenioService(ConvenioRepository repository){
        this.repository = repository;
    }

    public List<Convenio> getConvenios() {return repository.findAll();}


    public Optional<Convenio> getConvenioById(Long idConvenio) {
        return repository.findById(idConvenio);
    }

    public void validar(Convenio convenio) {
        if (convenio.getNome() == null || convenio.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
    }

    @Transactional
    public Convenio salvar(Convenio convenio) {
        validar(convenio);
        return repository.save(convenio);
    }
    @Transactional
    public void excluir(Convenio convenio) {
        Objects.requireNonNull(convenio.getIdConvenio());
        repository.delete(convenio);
    }
}
