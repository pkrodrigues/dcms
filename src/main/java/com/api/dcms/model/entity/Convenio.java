package com.api.dcms.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Convenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConvenio;
    private String nome;
    private String operadora;
    private String categoria;
    private Integer valorDesconto;

}
