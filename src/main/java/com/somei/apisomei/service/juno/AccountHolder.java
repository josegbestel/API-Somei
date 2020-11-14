package com.somei.apisomei.service.juno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.somei.apisomei.model.Pessoa;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountHolder implements Serializable {

    private String name;
    private String document;

    public AccountHolder() {
    }

    public AccountHolder(Pessoa pessoa){
        this.name = pessoa.getNome();
        this.document = String.valueOf(pessoa.getCpfNumber());
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
}
