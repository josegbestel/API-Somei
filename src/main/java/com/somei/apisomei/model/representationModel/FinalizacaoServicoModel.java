package com.somei.apisomei.model.representationModel;

import java.io.Serializable;

public class FinalizacaoServicoModel implements Serializable {

    private float custoExecucao;
    private String codigoServicoMunicipal;
    private AvaliacaoModel avaliacao;

    public float getCustoExecucao() {
        return custoExecucao;
    }

    public void setCustoExecucao(float custoExecucao) {
        this.custoExecucao = custoExecucao;
    }

    public String getCodigoServicoMunicipal() {
        return codigoServicoMunicipal;
    }

    public void setCodigoServicoMunicipal(String codigoServicoMunicipal) {
        this.codigoServicoMunicipal = codigoServicoMunicipal;
    }

    public AvaliacaoModel getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(AvaliacaoModel avaliacao) {
        this.avaliacao = avaliacao;
    }
}
