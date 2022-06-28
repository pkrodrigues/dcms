package com.api.dcms.api.dto;


import com.api.dcms.model.entity.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargoDTO {

    private int id_cargo;
    private String descricao_cargo;
    private String nome_cargo;


    public static com.api.dcms.api.dto.CargoDTO create(Cargo cargo){
        ModelMapper modelMapper = new ModelMapper();
        com.api.dcms.api.dto.CargoDTO dto = modelMapper.map(cargo, com.api.dcms.api.dto.CargoDTO.class);
        return dto;
    }
}