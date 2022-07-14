package com.api.dcms.api.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredenciaisDTO {
    private String login;
    private String senha;
    private boolean admin;
}