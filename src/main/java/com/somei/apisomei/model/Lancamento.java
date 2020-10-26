package com.somei.apisomei.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.somei.apisomei.util.CustomDate;
import com.somei.apisomei.util.CustomDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "lancamento")
public class Lancamento implements Serializable {

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
    private LocalDate dtVencimento;

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

    public CustomDate getDtVencimento() {
        return CustomDate.byLocalDate(dtVencimento);
    }

    public void setDtVencimento(CustomDate dtVencimento) {
        this.dtVencimento = dtVencimento.toLocalDate();
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
