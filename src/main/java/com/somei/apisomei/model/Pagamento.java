package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String idPagamento;
    private String idTransacao;
    private String idCobranca;
    private LocalDate dtRealizado;
    private LocalDate dtLancamento;
    private float valor;
    private float taxa;

    @OneToOne
    private Servico servico;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(String idPagamento) {
        this.idPagamento = idPagamento;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getIdCobranca() {
        return idCobranca;
    }

    public void setIdCobranca(String idCobranca) {
        this.idCobranca = idCobranca;
    }

    public LocalDate getDtRealizado() {
        return dtRealizado;
    }

    public void setDtRealizado(LocalDate dtRealizado) {
        this.dtRealizado = dtRealizado;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getTaxa() {
        return taxa;
    }

    public void setTaxa(float taxa) {
        this.taxa = taxa;
    }

    @JsonIgnore
    public Servico getServico() {
        return servico;
    }

    @JsonIgnore
    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
