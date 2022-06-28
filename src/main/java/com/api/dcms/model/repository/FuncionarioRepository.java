package com.api.dcms.model.repository;

import com.api.dcms.model.entity.Cargo;
import com.api.dcms.model.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

      List<Funcionario> findByCargo(Optional<Cargo> cargo);
}







