package com.api.dcms.service;

import org.springframework.stereotype.Service;
import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.Secretaria;
import java.util.Objects;
import com.api.dcms.model.repository.SecretariaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SecretariaService {
    
    private SecretariaRepository repository;

    public SecretariaService(SecretariaRepository repository) {
        this.repository = repository;
    }

    public List<Secretaria> getSecretarias() {
        return repository.findAll();
    }

    public Optional<Secretaria> getSecretariaById(Long idSecretaria) {
        return repository.findById(idSecretaria);
    }

    public void validar (Secretaria secretaria) {
        if (secretaria.getNome() == null || secretaria.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if(secretaria.getCpf() == null || secretaria.getCpf().trim().equals("")){
            throw new RegraNegocioException("CPF inválido");
        }
    }

    @Transactional
    public Secretaria salvar(Secretaria secretaria){
        validar(secretaria);
        return repository.save(secretaria);
    }
    @Transactional
    public void excluir(Secretaria secretaria) {
        Objects.requireNonNull(secretaria.getIdSecretaria());
        repository.delete(secretaria);
    }
}