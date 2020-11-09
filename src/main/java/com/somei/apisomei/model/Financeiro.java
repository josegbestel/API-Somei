package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "financeiro")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Financeiro implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private float metaMensal;

    @OneToMany(mappedBy = "financeiro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lancamento> lancamentos = new ArrayList<>();

    @OneToMany(mappedBy = "financeiro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DepositoBancario> depositos;

    @OneToOne
    private Profissional profissional;

    @OneToOne(mappedBy = "financeiro")
    private ContaBanco contaBanco;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getMetaMensal() {
        return metaMensal;
    }

    public void setMetaMensal(float metaMensal) {
        this.metaMensal = metaMensal;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public void addLancamento(Lancamento lancamento){
        lancamento.setFinanceiro(this);
        this.lancamentos.add(lancamento);
    }

    public List<DepositoBancario> getDepositos() {
        return depositos;
    }

    public void setDepositos(List<DepositoBancario> depositos) {
        this.depositos = depositos;
    }

    @JsonIgnore
    public Profissional getProfissional() {
        return profissional;
    }

    @JsonIgnore
    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public ContaBanco getContaBanco() {
        return contaBanco;
    }

    public void setContaBanco(ContaBanco contaBanco) {
        this.contaBanco = contaBanco;
    }
}
