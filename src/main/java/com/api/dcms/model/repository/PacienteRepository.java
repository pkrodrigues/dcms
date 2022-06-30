package com.api.dcms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.dcms.model.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {


    
}
