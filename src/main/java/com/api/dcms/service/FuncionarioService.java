package com.api.dcms.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.dcms.exception.RegraNegocioException;
import com.api.dcms.model.entity.Cargo;
import com.api.dcms.model.entity.Funcionario;
import com.api.dcms.model.repository.FuncionarioRepository;


@Service
public class FuncionarioService {

    private FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> getFuncionario() {
        return repository.findAll();
    }

    public Optional<Funcionario> getFuncionarioById(Long id) {
        return repository.findById(id);
    }

    public List<Funcionario> getFuncionarioByCargo(Optional<Cargo> cargo) {
        return repository.findByCargo(cargo);

    }

    @Transactional
    public Funcionario salvar(Funcionario funcionario) {
        validar(funcionario);
        return repository.save(funcionario);
    }

    @Transactional
    public void excluir(Funcionario funcionario) {
        Objects.requireNonNull(funcionario.getIdFuncionario());
        repository.delete(funcionario);
    }

    public void validar(Funcionario funcionario) {

        if (funcionario.getNome() == null || funcionario.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (funcionario.getCargo() == null || funcionario.getCargo().getIdCargo() == null || funcionario.getCargo().getIdCargo() == 0) {
            throw new RegraNegocioException("Cargo inválido");
        }
    }
}