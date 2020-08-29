package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne(mappedBy = "profissional")
    private Financeiro financeiro;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaMei categoria;


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Financeiro getFinanceiro() {
        return financeiro;
    }

    public void setFinanceiro(Financeiro financeiro) {
        this.financeiro = financeiro;
    }
}
