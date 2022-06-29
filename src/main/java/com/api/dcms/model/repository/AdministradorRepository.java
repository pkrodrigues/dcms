package com.api.dcms.model.repository;

import com.api.dcms.model.entity.Cargo;
import com.api.dcms.model.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrator, Long> {

      List<Administrator> findByCargo(Optional<Cargo> cargo);
}







