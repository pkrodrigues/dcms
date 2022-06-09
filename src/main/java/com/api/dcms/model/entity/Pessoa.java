package com.api.dcms.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Getter
@Setter

public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Date dataNasc;
    private String rg;
    private String cpf;
    private String sexo;
    private String telefone;
    private String email;
    private Long cep;
    private String rua;
    private Long numeroResidencia;
    private String Complemento;
    private String bairro;
    private String cidade;

}

