package com.api.dcms.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Medico extends Colaborador {

    private String crm;
    private String especialidade;

}
