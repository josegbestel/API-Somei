package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.somei.apisomei.model.enums.TipoConta;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "conta_banco")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ContaBanco implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String nBanco;

    @NotBlank
    private String nAgencia;

    @NotBlank
    private String nConta;
    private String nComplementarConta;

    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @OneToOne
    private Financeiro financeiro;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getnBanco() {
        return nBanco;
    }

    public void setnBanco(String nBanco) {
        this.nBanco = nBanco;
    }

    public String getnAgencia() {
        return nAgencia;
    }

    public void setnAgencia(String nAgencia) {
        this.nAgencia = nAgencia;
    }

    public String getnConta() {
        return nConta;
    }

    public void setnConta(String nConta) {
        this.nConta = nConta;
    }

    public String getnComplementarConta() {
        return nComplementarConta;
    }

    public void setnComplementarConta(String nComplementarConta) {
        this.nComplementarConta = nComplementarConta;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    @JsonIgnore
    public Financeiro getFinanceiro() {
        return financeiro;
    }

    @JsonIgnore
    public void setFinanceiro(Financeiro financeiro) {
        this.financeiro = financeiro;
    }
}
