package com.api.dcms.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter


public class Paciente extends Pessoa{

    @ManyToOne
    private Convenio convenio;


    
}
