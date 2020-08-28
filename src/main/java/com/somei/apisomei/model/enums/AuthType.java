package com.somei.apisomei.model.enums;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

public enum AuthType implements Serializable {


    USUARIO,
    SUPORTE,
    ADMIN;

    private String authrole;

    public String getAuthrole() {
        return authrole;
    }

    public void setAuthrole(String authrole) {
        this.authrole = authrole;
    }
}
