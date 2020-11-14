package com.somei.apisomei.service.juno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Split implements Serializable {

    private String recipientToken;
    private int percentage;
    private boolean amountRemainder;
    private boolean chargeFee;

    public Split(String recipientToken, int percentage, boolean amountRemainder) {
        this.recipientToken = recipientToken;
        this.percentage = percentage;
        this.amountRemainder = amountRemainder;
        this.chargeFee = true;
    }

    public String getRecipientToken() {
        return recipientToken;
    }

    public void setRecipientToken(String recipientToken) {
        this.recipientToken = recipientToken;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public boolean isAmountRemainder() {
        return amountRemainder;
    }

    public void setAmountRemainder(boolean amountRemainder) {
        this.amountRemainder = amountRemainder;
    }

    public boolean isChargeFee() {
        return chargeFee;
    }

    public void setChargeFee(boolean chargeFee) {
        this.chargeFee = chargeFee;
    }
}
