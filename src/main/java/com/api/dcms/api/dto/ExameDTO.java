package com.api.dcms.api.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.modelmapper.ModelMapper;

import com.api.dcms.model.entity.Exame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExameDTO {
    private Long idExame;
    private LocalDate data;
    private LocalTime horario;
    private String nome;
    private String descricao;
    private double valor;
    private Long idPaciente;
    private Long idMedico;
    private Long idConvenio;

    public static ExameDTO create(Exame exame) {
        ModelMapper modelMapper = new ModelMapper();
        ExameDTO dto = modelMapper.map(exame, ExameDTO.class);
        dto.getIdConvenio();
        dto.getIdPaciente();
        dto.getIdMedico();
        return dto;
    }
}

