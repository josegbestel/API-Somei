package com.somei.apisomei.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonObject;
import com.somei.apisomei.model.Servico;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceInvoiceNfeDTO implements Serializable {

    public String id;
    public String environment;
    public Double number;
    public String createdOn;

    public String cityServiceCode;      // Código do serviço de acordo com a cidade
    public String description;          // Descrição do serviço
    public double servicesAmount;       // Valor do serviço
    public ProviderNfeDTO provider;     // Dados do emitente do serviço
    public BorrowerNfeDTO borrower;     // Dados do tomador do serviço

    public ServiceInvoiceNfeDTO() {
    }

    public ServiceInvoiceNfeDTO(Servico servico){
        this.cityServiceCode = servico.getCodigoServicoMunicipal();
        this.description = servico.getDescricao();
        this.servicesAmount = servico.getRespostaEscolhida().getValor();
        this.borrower = new BorrowerNfeDTO(servico.getSolicitante(), servico.getLocalizacao());
    }

    public JsonObject toJson() {
        JsonObject serviceInvoice = new JsonObject();

        serviceInvoice.addProperty("cityServiceCode", this.getCityServiceCode());
        serviceInvoice.addProperty("description", this.getDescription());
        serviceInvoice.addProperty("servicesAmount", this.getServicesAmount());
        serviceInvoice.add("borrower", this.getBorrower().toJson());

        return serviceInvoice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getCityServiceCode() {
        return cityServiceCode;
    }

    public void setCityServiceCode(String cityServiceCode) {
        this.cityServiceCode = cityServiceCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getServicesAmount() {
        return servicesAmount;
    }

    public void setServicesAmount(double servicesAmount) {
        this.servicesAmount = servicesAmount;
    }

    public ProviderNfeDTO getProvider() {
        return provider;
    }

    public void setProvider(ProviderNfeDTO provider) {
        this.provider = provider;
    }

    public BorrowerNfeDTO getBorrower() {
        return borrower;
    }

    public void setBorrower(BorrowerNfeDTO borrower) {
        this.borrower = borrower;
    }
}
