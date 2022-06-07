package com.api.dcms.model.entity;

import com.api.dcms.model.entity.Convenio;
import com.api.dcms.model.entity.Medico;
import com.api.dcms.model.entity.Paciente;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgenda;

    private Date data;
    private Time horario;
    private String Observacao;
    @OneToOne
    private Convenio convenio;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Medico medico;

}