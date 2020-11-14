package com.somei.apisomei.service.juno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.somei.apisomei.model.Pessoa;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LegalRepresentative implements Serializable {

    private String name;
    private String document;
    private String birthDate;

    public LegalRepresentative() {
    }

    public LegalRepresentative(Pessoa pessoa) {
        this.name = pessoa.getNome();
        this.document = String.valueOf(pessoa.getCpfNumber());
        this.birthDate = pessoa.getDtNascimento().toString();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
