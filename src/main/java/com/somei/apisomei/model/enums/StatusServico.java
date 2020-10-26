package com.somei.apisomei.model.enums;

import java.io.Serializable;

public enum StatusServico implements Serializable {

    NOVO,
    SOLICITADO,     //Quando o orçamento é enviado para os profissionais
    RESPONDIDO,     //Quando pelo menos 1 profissional respondeu
    PENDENTE,       //Quando o solicitante escolhe uma resposta
    FINALIZADO,     //Quando ambos avaliam o Servico
    CANCELADO;      //Quando, em qualquer momento, o serviço é cancelado

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
