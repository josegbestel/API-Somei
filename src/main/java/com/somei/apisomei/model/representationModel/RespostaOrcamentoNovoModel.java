package com.somei.apisomei.model.representationModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RespostaOrcamentoNovoModel implements Serializable {

    @NotNull
    private long orcamentoId;

    @NotNull
    private long respostaId;

    @NotNull
    private float valor;

    private String observacao;

    @NotNull
    private long AgendaId;

    public long getOrcamentoId() {
        return orcamentoId;
    }

    public void setOrcamentoId(long orcamentoId) {
        this.orcamentoId = orcamentoId;
    }

    public long getRespostaId() {
        return respostaId;
    }

    public void setRespostaId(long respostaId) {
        this.respostaId = respostaId;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public long getAgendaId() {
        return AgendaId;
    }

    public void setAgendaId(long agendaId) {
        AgendaId = agendaId;
    }
}
