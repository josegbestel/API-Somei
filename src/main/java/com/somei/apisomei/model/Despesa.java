package com.somei.apisomei.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "despesa")
public class Despesa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @NotNull
    private String descricao;

    @NotNull
    private float valor;

    @NotNull
    private boolean fixa;

    @NotNull
    private LocalDateTime dtVencimento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "financeiro_id", nullable = false)
    private Financeiro financeiro;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public boolean isFixa() {
        return fixa;
    }

    public void setFixa(boolean fixa) {
        this.fixa = fixa;
    }

    public LocalDateTime getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(LocalDateTime dtVencimento) {
        this.dtVencimento = dtVencimento;
    }
}
