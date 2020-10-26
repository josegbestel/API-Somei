package com.somei.apisomei.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonObject;
import com.somei.apisomei.model.Localizacao;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressNfeDTO implements Serializable {

    public String country;
    public String postalCode;
    public String street;
    public String number;
    public String additionalInformation;
    public String district;
    public String state;
    public CityNfeDTO city;

    public AddressNfeDTO() {
    }

    public AddressNfeDTO(Localizacao localizacao) {
        this.country = "BRA";
        this.postalCode = localizacao.getCep();
        this.street = localizacao.getLogradouro();
        this.number = String.valueOf(localizacao.getNumero());
        this.additionalInformation = localizacao.getComplemento() == null ? "" : localizacao.getComplemento();
        this.district = localizacao.getBairro();
        this.state = localizacao.getUf();
        this.city = new CityNfeDTO(localizacao);
    }

    public JsonObject toJson(){
        JsonObject address = new JsonObject();

        address.addProperty("country", "BRA");
        address.addProperty("postalCode", this.getPostalCode());
        address.addProperty("street", this.getStreet());
        address.addProperty("number", this.getNumber());
        address.addProperty("additionalInformation", this.getNumber());
        address.addProperty("district", this.getDistrict());
        address.addProperty("state", this.getState());
        address.add("city", this.getCity().toJson());

        return address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CityNfeDTO getCity() {
        return city;
    }

    public void setCity(CityNfeDTO city) {
        this.city = city;
    }
}
