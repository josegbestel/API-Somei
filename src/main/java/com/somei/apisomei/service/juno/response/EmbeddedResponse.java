package com.somei.apisomei.service.juno.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmbeddedResponse implements Serializable {

    private List<ChargeResponse> charges;

    public EmbeddedResponse() {
    }

    public List<ChargeResponse> getCharges() {
        return charges;
    }

    public void setCharges(List<ChargeResponse> charges) {
        this.charges = charges;
    }
}
