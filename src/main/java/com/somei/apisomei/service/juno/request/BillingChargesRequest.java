package com.somei.apisomei.service.juno.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.somei.apisomei.model.Servico;
import com.somei.apisomei.model.Solicitante;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BillingChargesRequest implements Serializable {

    private String name;
    private String document;
    private String email;
    private String phone;
    private String birthDate;
    private boolean notify;

    public BillingChargesRequest() {
    }

    public BillingChargesRequest(Servico servico) {
        Solicitante solicitante = servico.getSolicitante();

        this.name = solicitante.getNome();
        this.document = solicitante.getCpf()
                .replaceAll("\\.", "")
                .replaceAll("-", "");
        this.email = solicitante.getEmail();
        this.phone = solicitante.getTelefone()
                .replaceAll("\\.", "")
                .replaceAll("-", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "");
        this.birthDate = solicitante.getDataNascimento().toString();
        this.notify = false;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }
}
