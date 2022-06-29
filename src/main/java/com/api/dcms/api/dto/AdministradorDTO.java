package com.api.dcms.api.dto;

import com.api.dcms.model.entity.Administrator;
import com.api.dcms.model.entity.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdministradorDTO {
    private Long idAdministrador;
    private String complemento;
    private String bairro;
    private Long cep;
    private String cidade;
    private String cpf;
    private Date data_nasc;
    private String email;
    private String nome;
    private Long numero_residencia;
    private String rg;
    private String rua;
    private String sexo;
    private String telefone;
    private  Date data_admissao;
    private  Date data_saida;
    private double salario;
    private String tipo_contratacao;
    private Long idCargo;


    public static AdministradorDTO create(Administrator funcionario) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(funcionario, AdministradorDTO.class);
        }
    }

