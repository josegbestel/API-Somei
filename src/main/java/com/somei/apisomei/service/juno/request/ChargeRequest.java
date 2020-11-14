package com.somei.apisomei.service.juno.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.somei.apisomei.config.ApplicationConfig;
import com.somei.apisomei.model.Servico;
import com.somei.apisomei.service.juno.Split;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargeRequest implements Serializable {

    private String description;
    private Float amount;
    private List<String> paymentTypes = new ArrayList<>();
    private List<Split> split = new ArrayList<>();

    public ChargeRequest() {
    }

    public ChargeRequest(Servico servico) {
        this.paymentTypes.add("CREDIT_CARD");
        this.description = servico.getId() + "-" + servico.getDescricao();
        this.amount = Float.parseFloat(String.valueOf(servico.getRespostaEscolhida().getValor()));
        String tokenProf = servico.getProfissional().getResourceTokenJuno();
        Split splitProf = new Split(tokenProf, 85, true);
        Split splitSomei = new Split(ApplicationConfig.TOKEN_JUNO_PRIVATE, 15, false);
        this.split.add(splitProf);
        this.split.add(splitSomei);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public List<String> getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(List<String> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public List<Split> getSplit() {
        return split;
    }

    public void setSplit(List<Split> split) {
        this.split = split;
    }
}
