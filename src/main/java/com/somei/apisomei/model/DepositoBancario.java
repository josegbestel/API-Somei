package com.somei.apisomei.model;

import com.somei.apisomei.model.enums.StatusDeposito;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "deposito_bancario")
public class DepositoBancario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private float valor;

    @NotNull
    private LocalDateTime dataPrevista;

    @NotNull
    private LocalDateTime dataDeposito;

    @Enumerated(EnumType.STRING)
    private StatusDeposito status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "financeiro_id", nullable = false)
    private Financeiro financeiro;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(LocalDateTime dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public LocalDateTime getDataDeposito() {
        return dataDeposito;
    }

    public void setDataDeposito(LocalDateTime dataDeposito) {
        this.dataDeposito = dataDeposito;
    }

    public StatusDeposito getStatus() {
        return status;
    }

    public void setStatus(StatusDeposito status) {
        this.status = status;
    }
}
