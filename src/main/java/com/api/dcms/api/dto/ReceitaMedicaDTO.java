package com.api.dcms.api.dto;

import com.api.dcms.model.entity.Medico;
import com.api.dcms.model.entity.Paciente;
import com.api.dcms.model.entity.ReceitaMedica;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.ManyToOne;
import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaMedicaDTO {
    private Long idReceitaMed;
    private Long idMedico;
    private Long idPaciente;
    private Date dtEmissaoReceita;
    private String presquicao;

    public static ReceitaMedicaDTO create(ReceitaMedica receitaMedica) {
        ModelMapper modelMapper = new ModelMapper();
        ReceitaMedicaDTO dto = modelMapper.map(receitaMedica, ReceitaMedicaDTO.class);
        return dto;
    }
}