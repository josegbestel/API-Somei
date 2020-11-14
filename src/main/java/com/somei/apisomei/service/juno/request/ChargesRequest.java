package com.somei.apisomei.service.juno.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.somei.apisomei.model.Servico;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargesRequest implements Serializable {

    private ChargeRequest charge;
    private BillingChargesRequest billing;

    public ChargesRequest() {
    }

    public ChargesRequest(Servico servico) {
        this.charge = new ChargeRequest(servico);
        this.billing = new BillingChargesRequest(servico);
    }

    public ChargeRequest getCharge() {
        return charge;
    }

    public void setCharge(ChargeRequest charge) {
        this.charge = charge;
    }

    public BillingChargesRequest getBilling() {
        return billing;
    }

    public void setBilling(BillingChargesRequest billing) {
        this.billing = billing;
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
