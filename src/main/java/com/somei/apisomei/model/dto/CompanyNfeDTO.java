package com.somei.apisomei.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonObject;
import com.somei.apisomei.model.Profissional;
import springfox.documentation.spring.web.json.Json;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyNfeDTO implements Serializable {

    public String id;
    public String name;
    public String tradeName;
    public long federalTaxNumber;
    public String email;
    public AddressNfeDTO address;

    public CompanyNfeDTO() {
    }

    public CompanyNfeDTO(Profissional profissional){
        this.name = profissional.getNome();
        this.tradeName = profissional.getNomeFantasia();
        this.federalTaxNumber = profissional.getCnpjNumber();
        this.email = profissional.getEmail();
        this.address = new AddressNfeDTO(profissional.getLocalizacao());
    }

    public JsonObject toJson(){
        JsonObject company = new JsonObject();

        company.addProperty("name", this.getName());
        company.addProperty("tradeName", this.getTradeName());
        company.addProperty("federalTaxNumber", this.getFederalTaxNumber());
        company.addProperty("email", this.getEmail());
        company.add("address", this.getAddress().toJson());

        return company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public long getFederalTaxNumber() {
        return federalTaxNumber;
    }

    public void setFederalTaxNumber(long federalTaxNumber) {
        this.federalTaxNumber = federalTaxNumber;
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
