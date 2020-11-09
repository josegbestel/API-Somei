package com.somei.apisomei.model.enums;

public enum TipoConta {
    POUPANCA,
    CORRENTE;

    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
