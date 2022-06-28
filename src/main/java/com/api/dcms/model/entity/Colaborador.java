package com.api.dcms.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Getter
@Setter
public abstract class Colaborador extends Pessoa{
    private double salario;
    private Date dataAdmissao;
    private Date dataSaida;
}
