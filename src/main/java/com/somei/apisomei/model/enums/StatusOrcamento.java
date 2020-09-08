package com.somei.apisomei.model.enums;

import java.io.Serializable;

public enum StatusOrcamento implements Serializable {

    NOVO,
    SOLICITADO,
    RESPONDIDO,
    CONFIRMADO,
    PENDENTE,
    FINALIZADO,
    CANCELADO;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
