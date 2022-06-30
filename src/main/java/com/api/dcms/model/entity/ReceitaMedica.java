package com.api.dcms.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReceitaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceitaMed;
    @ManyToOne
    private Medico medico;
    @ManyToOne
    private Paciente paciente;
    private Date dtEmissaoReceita;
    private String prescricao;
}
