package com.somei.apisomei.service.juno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.somei.apisomei.model.ContaBanco;
import com.somei.apisomei.model.Profissional;
import com.somei.apisomei.model.enums.TipoConta;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BankAccount implements Serializable {

    private String bankNumber;
    private String agencyNumber;
    private String accountNumber;
    private AccountType accountType;
    private AccountHolder accountHolder;

    public BankAccount(Profissional profissional) {
        ContaBanco contaBanco = profissional.getFinanceiro().getContaBanco();
        this.bankNumber = contaBanco.getnBanco();
        this.agencyNumber = contaBanco.getnAgencia();
        this.accountNumber = contaBanco.getnConta();
        this.accountType = contaBanco.getTipoConta() == TipoConta.CORRENTE
                ? AccountType.CHECKING
                : AccountType.SAVINGS;
        this.accountHolder = new AccountHolder(profissional);
    }

    public BankAccount() {
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getAgencyNumber() {
        return agencyNumber;
    }

    public void setAgencyNumber(String agencyNumber) {
        this.agencyNumber = agencyNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }
}
