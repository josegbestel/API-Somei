package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "resposta_orcamento")
public class RespostaOrcamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime dtResposta;

    private double valor;

    private String observacao;

    private boolean escolhida;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;

    @ManyToOne(fetch = FetchType.LAZY)
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "agenda_id")
    Agenda agenda;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDtResposta() {
        return dtResposta;
    }

    public void setDtResposta(LocalDateTime dtResposta) {
        this.dtResposta = dtResposta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isEscolhida() {
        return escolhida;
    }

    public void setEscolhida(boolean escolhida) {
        this.escolhida = escolhida;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    private Servico getOrcamento() {
        return servico;
    }

    @JsonIgnore
    public Servico getOcamentoClean() {
        Servico servico = this.servico;
        servico.setRespostas(new ArrayList<>());
        return servico;
    }

    public void setOrcamento(Servico servico) {
        this.servico = servico;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }


}
