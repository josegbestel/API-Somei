package com.somei.apisomei.service.juno.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceResponse implements Serializable {

    private float balance;
    private float withheldBalance;
    private float transferableBalance;

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getWithheldBalance() {
        return withheldBalance;
    }

    public void setWithheldBalance(float withheldBalance) {
        this.withheldBalance = withheldBalance;
    }

    public float getTransferableBalance() {
        return transferableBalance;
    }

    public void setTransferableBalance(float transferableBalance) {
        this.transferableBalance = transferableBalance;
    }
}
