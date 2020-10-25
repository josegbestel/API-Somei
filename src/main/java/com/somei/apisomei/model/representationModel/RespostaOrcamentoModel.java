package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.Servico;
import com.somei.apisomei.model.Profissional;
import com.somei.apisomei.model.RespostaOrcamento;

import java.time.LocalDateTime;

public class RespostaOrcamentoModel {

    private long id;
    private LocalDateTime dtResposta;
    private double valor;
    private String observacao;
    private boolean escolhida;
    private Profissional profissional;
    private Servico servico;

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

    public Servico getOrcamento() {
        return servico;
    }

    public void setOrcamento(Servico servico) {
        this.servico = servico;
    }

    static public RespostaOrcamentoModel toModel(RespostaOrcamento resposta){
        RespostaOrcamentoModel model = new RespostaOrcamentoModel();
        model.setId(resposta.getId());
        model.setDtResposta(resposta.getDtResposta());
        model.setEscolhida(resposta.isEscolhida());
        model.setValor(resposta.getValor());
        model.setObservacao(resposta.getObservacao());
        model.setProfissional(resposta.getProfissional());
        model.setOrcamento(resposta.getOcamentoClean());

        return model;
    }

}
