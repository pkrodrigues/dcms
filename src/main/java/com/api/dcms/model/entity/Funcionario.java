package com.api.dcms.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario extends Colaborador{
    private String tipoContratacao;

    @ManyToOne
    private Cargo cargo;
}
