package com.somei.apisomei.model.enums;

import java.io.Serializable;

public enum StatusDeposito implements Serializable {

    AGENDADO,
    REALIZADO,
    CANCELADO;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
