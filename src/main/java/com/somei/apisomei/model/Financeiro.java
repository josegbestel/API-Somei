package com.somei.apisomei.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "financeiro")
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

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }
}
