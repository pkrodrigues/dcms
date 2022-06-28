package com.api.dcms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.dcms.model.entity.Secretaria;

public interface SecretariaRepository extends JpaRepository<Secretaria,Long> {
    
}
