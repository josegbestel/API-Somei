package com.somei.apisomei.model;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "solicitante")
public class Solicitante extends Pessoa implements Serializable {

    @NotNull
    @NotBlank
    @Column(unique = true)
    @CPF(message = "CPF invalido")
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
