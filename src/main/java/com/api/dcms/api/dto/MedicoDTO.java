package com.api.dcms.api.dto;

import com.api.dcms.model.entity.Medico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO {

    private int medico_id;
    private String rg;
    private String sexo;
    private String telefone;
    private  Date data_admissao;
    private  Date data_saida;
    private double salario;
    private int crm;
    private String cpf;
    private String especialidade;
    private String cidade;
    private  Date data_nasc;
    private String email;
    private String nome;
    private String complemento;
    private String bairro;
    private Long cep;
    private Long numero_residencia;
    private String rua;
    private String uf;
    private Long idConvenio;



    public static MedicoDTO create(Medico medico){
        ModelMapper modelMapper = new ModelMapper();
        MedicoDTO dto = modelMapper.map(medico, MedicoDTO.class);
        dto.getIdConvenio();
        return dto;
    }
}
