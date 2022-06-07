package com.api.dcms.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Getter
@Setter
public abstract class Colaborador extends Pessoa{

    private double salario;
    @Column(name="data_admissao")
    private Date dataAdmissao;
    private Date dataSaida;
}
