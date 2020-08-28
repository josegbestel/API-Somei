package com.somei.apisomei.model.representationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PessoaLoginModel {


    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotBlank
    @NotNull
    @JsonIgnore
    @Size(max = 8, min=8)
    private String senha;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
