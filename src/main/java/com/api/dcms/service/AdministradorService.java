package com.api.dcms.service;

import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.Cargo;
import com.api.dcms.model.entity.Administrator;
import com.api.dcms.model.repository.AdministradorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class AdministradorService {

    private AdministradorRepository repository;

    public AdministradorService(AdministradorRepository repository) {
        this.repository = repository;
    }

    public List<Administrator> getAdministrador() {
        return repository.findAll();
    }

    public Optional<Administrator> getAdministradorById(Long id) {
        return repository.findById(id);
    }

    public List<Administrator> getAdministradorByCargo(Optional<Cargo> cargo) {
        return repository.findByCargo(cargo);

    }

    @Transactional
    public Administrator salvar(Administrator funcionario) {
        validar(funcionario);
        return repository.save(funcionario);
    }

    @Transactional
    public void excluir(Administrator funcionario) {
        Objects.requireNonNull(funcionario.getIdAdministrador());
        repository.delete(funcionario);
    }

    public void validar(Administrator funcionario) {

        if (funcionario.getNome() == null || funcionario.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (funcionario.getCargo() == null || funcionario.getCargo().getIdCargo() == null || funcionario.getCargo().getIdCargo() == 0) {
            throw new RegraNegocioException("Cargo inválido");
        }
    }
}