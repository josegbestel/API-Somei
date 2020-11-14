package com.somei.apisomei.service.juno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BankAccount implements Serializable {

    private String bankNumber;
    private String agencyNumber;
    private String accountNumber;
    private AccountType accountType;
    private AccountHolder accountHolder;
}
