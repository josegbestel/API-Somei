package com.somei.apisomei.service.juno.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferRequest implements Serializable {

    private String type;
    private float amount;

    public TransferRequest() {
    }

    public TransferRequest(float valor) {
        this.type = "DEFAULT_BANK_ACCOUNT";
        this.amount = valor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String toJson(){
        ObjectMapper mapper = new ObjectMapper();

        try {
            String jsonString = mapper.writeValueAsString(this);

            System.out.println("Json de ChargesRequest: \n" + jsonString);
            return jsonString;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
