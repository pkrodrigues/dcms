package com.api.dcms.model.repository;

import com.api.dcms.model.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository <Agenda, Long> {

}
