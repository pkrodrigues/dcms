package com.api.dcms.api.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.api.dcms.model.entity.ReceitaMedica;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaMedicaDTO {
    private Long idReceitaMed;
    private Long idMedico;
    private Long idPaciente;
    private Date dtEmissaoReceita;
    private String prescricao;

    public static ReceitaMedicaDTO create(ReceitaMedica receitaMedica) {
        ModelMapper modelMapper = new ModelMapper();
        ReceitaMedicaDTO dto = modelMapper.map(receitaMedica, ReceitaMedicaDTO.class);
        return dto;
    }
}