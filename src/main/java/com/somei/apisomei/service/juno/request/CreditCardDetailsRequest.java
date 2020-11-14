package com.somei.apisomei.service.juno.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditCardDetailsRequest implements Serializable {

    private String creditCardHash;

    public CreditCardDetailsRequest() {
    }

    public CreditCardDetailsRequest(String creditCardHash) {
        this.creditCardHash = creditCardHash;
    }

    public String getCreditCardHash() {
        return creditCardHash;
    }

    public void setCreditCardHash(String creditCardHash) {
        this.creditCardHash = creditCardHash;
    }
}
