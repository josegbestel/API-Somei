package com.somei.apisomei.service.juno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.somei.apisomei.model.Localizacao;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address implements Serializable {

    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String postCode;

    public Address() {
    }

    public Address(Localizacao localizacao){
        this.postCode = localizacao.getCep()
                .replaceAll("-", "")
                .replaceAll("\\.", "");
        this.street = localizacao.getLogradouro();
        this.number = String.valueOf(localizacao.getNumero());
        this.complement = localizacao.getComplemento() == null ? "-" : localizacao.getComplemento();
        this.neighborhood = localizacao.getBairro();
        this.city = localizacao.getCidade();
        this.state = localizacao.getUf();
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

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
