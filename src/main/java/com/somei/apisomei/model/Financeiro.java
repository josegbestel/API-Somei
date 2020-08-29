package com.somei.apisomei.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "financeiro")
public class Financeiro implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @NotNull
    private float metaMensal;

    @OneToMany(mappedBy = "financeiro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Despesa> despesas;

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

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    public List<DepositoBancario> getDepositos() {
        return depositos;
    }

    public void setDepositos(List<DepositoBancario> depositos) {
        this.depositos = depositos;
    }
}
