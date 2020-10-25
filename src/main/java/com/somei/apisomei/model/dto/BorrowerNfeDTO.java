package com.somei.apisomei.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.somei.apisomei.model.Localizacao;
import com.somei.apisomei.model.Solicitante;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BorrowerNfeDTO implements Serializable {

    public String type;     //Tipo do tomador de serviço (opções: 'Undefined', 'NaturalPerson', 'LegalEntity')
    public Long federalTaxNumber; //CPF ou CNPJ em números
    public String name;
    public String email;
    public AddressNfeDTO address;

    public BorrowerNfeDTO() {
    }

    public BorrowerNfeDTO(Solicitante solicitante, Localizacao localizacao) {
        this.type = "NaturalPerson";
        this.federalTaxNumber = solicitante.getCpfNumber();
        this.name = solicitante.getNome();
        this.email = solicitante.getEmail();
        this.address = new AddressNfeDTO(localizacao);
    }

    public JsonObject toJson() {
        JsonObject borrower = new JsonObject();

        borrower.addProperty("type", this.getType());
        borrower.addProperty("federalTaxNumber", this.getFederalTaxNumber());
        borrower.addProperty("name", this.getName());
        borrower.addProperty("email", this.getEmail());
        borrower.add("address", this.getAddress().toJson());

        return borrower;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getFederalTaxNumber() {
        return federalTaxNumber;
    }

    public void setFederalTaxNumber(Long federalTaxNumber) {
        this.federalTaxNumber = federalTaxNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressNfeDTO getAddress() {
        return address;
    }

    public void setAddress(AddressNfeDTO address) {
        this.address = address;
    }
}
