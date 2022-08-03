package com.api.dcms.model.repository;

import com.api.dcms.model.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.dcms.model.entity.ReceitaMedica;

import java.util.Optional;

public interface ReceitaMedicaRepository extends JpaRepository<ReceitaMedica, Long> {
}
