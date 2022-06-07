package com.api.dcms.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExame;

    private String nome;
    private String descricao;
    private double valor;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Medico medico;
    private String convenio;





}