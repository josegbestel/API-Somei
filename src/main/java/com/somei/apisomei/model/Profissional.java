package com.somei.apisomei.model;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "profissional")
public class Profissional extends Pessoa implements Serializable {

    @NotBlank
    @NotNull
    @CNPJ
    private String cnpj;
//    private Profissao profissao;
//    private Financeiro financeiro;


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
