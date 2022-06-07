package com.api.dcms.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Timer;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;

    @ManyToOne
    private Medico medico;
    @ManyToOne
    private Paciente paciente;

    private String procedimento;
    private Date dataConsulta;
    private Date horario;
    private String Observacao;
    private String convenio;
    private double valor;
    private double valorTotal;

}
