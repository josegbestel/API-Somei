package com.somei.apisomei.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderNfeDTO implements Serializable {

    private Long federalTaxNumber;

    public ProviderNfeDTO() {
    }

    public Long getFederalTaxNumber() {
        return federalTaxNumber;
    }

    public void setFederalTaxNumber(Long federalTaxNumber) {
        this.federalTaxNumber = federalTaxNumber;
    }
}
