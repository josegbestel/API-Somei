package com.somei.apisomei.service.juno.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.somei.apisomei.model.Servico;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequest implements Serializable {

    private String chargeId;
    private BillingPaymentRequest billing;
    private CreditCardDetailsRequest creditCardDetails;

    public PaymentRequest() {
    }

    public PaymentRequest(Servico servico, String cartaoHash) {
        this.chargeId = servico.getPagamento().getIdCobranca();
        this.billing = new BillingPaymentRequest(servico);
        this.creditCardDetails = new CreditCardDetailsRequest(cartaoHash);
    }



    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public BillingPaymentRequest getBilling() {
        return billing;
    }

    public void setBilling(BillingPaymentRequest billing) {
        this.billing = billing;
    }

    public CreditCardDetailsRequest getCreditCardDetails() {
        return creditCardDetails;
    }

    public void setCreditCardDetails(CreditCardDetailsRequest creditCardDetails) {
        this.creditCardDetails = creditCardDetails;
    }
}
