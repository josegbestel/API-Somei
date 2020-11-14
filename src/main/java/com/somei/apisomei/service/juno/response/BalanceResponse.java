package com.somei.apisomei.service.juno.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceResponse implements Serializable {

    private Float balance;
    private Float withheldBalance;
    private Float transferableBalance;

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Float getWithheldBalance() {
        return withheldBalance;
    }

    public void setWithheldBalance(Float withheldBalance) {
        this.withheldBalance = withheldBalance;
    }

    public Float getTransferableBalance() {
        return transferableBalance;
    }

    public void setTransferableBalance(Float transferableBalance) {
        this.transferableBalance = transferableBalance;
    }

}
