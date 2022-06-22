package com.api.dcms.api.dto;

import com.api.dcms.model.entity.Convenio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConvenioDTO {

    private Long idConvenio;
    private String nome;
    private String operadora;
    private String categoria;
    private Integer valorDesconto;


    public static ConvenioDTO create(Convenio convenio) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(convenio, ConvenioDTO.class);
    }
}
