package com.somei.apisomei.service.juno.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.somei.apisomei.model.Servico;
import com.somei.apisomei.model.Solicitante;
import com.somei.apisomei.service.juno.Address;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BillingPaymentRequest implements Serializable {

    private String email;
    private Address address;
    private boolean delayed;

    public BillingPaymentRequest() {
    }

    public BillingPaymentRequest(Servico servico) {
        this.email = servico.getSolicitante().getEmail();
        this.address = new Address(servico.getLocalizacao());
        this.delayed = false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isDelayed() {
        return delayed;
    }

    public void setDelayed(boolean delayed) {
        this.delayed = delayed;
    }
}
