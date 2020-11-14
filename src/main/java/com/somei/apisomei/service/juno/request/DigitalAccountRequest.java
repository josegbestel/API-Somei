package com.somei.apisomei.service.juno.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.somei.apisomei.model.Pessoa;
import com.somei.apisomei.model.Profissional;
import com.somei.apisomei.service.juno.Address;
import com.somei.apisomei.service.juno.BankAccount;
import com.somei.apisomei.service.juno.LegalRepresentative;

import java.io.Serializable;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DigitalAccountRequest implements Serializable {

    private String type;
    private String name;
    private String document;
    private String email;
    private String birthDate;
    private String phone;
    private int businessArea;
    private String linesOfBusiness;
    private String companyType;
    private LegalRepresentative legalRepresentative;
    private Address address;
    private BankAccount bankAccount;
    private boolean emailOptOut;
    private boolean autoApprove;
    private boolean autoTransfer;

    public DigitalAccountRequest(Profissional profissional) {
        //Default infos
        this.type = "PAYMENT";
        this.companyType = "MEI";
        this.businessArea = 2033;
        this.emailOptOut = false;
        this.autoApprove = false;
        this.autoTransfer = false;

        this.name = profissional.getNome();
        this.document = profissional.getCnpj()
                .replaceAll("\\.", "")
                .replaceAll("-", "")
                .replaceAll("/", "");
        this.email = profissional.getEmail();
        this.birthDate = profissional.getDtNascimento().toString();
        this.phone = profissional.getTelefone()
                .replaceAll("\\.", "")
                .replaceAll("-", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "");
        this.linesOfBusiness = profissional.getCategoria().getTitulo();
        this.legalRepresentative = new LegalRepresentative(profissional);
        this.address = new Address(profissional.getLocalizacao());
        this.bankAccount = new BankAccount(profissional);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(int businessArea) {
        this.businessArea = businessArea;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getLinesOfBusiness() {
        return linesOfBusiness;
    }

    public void setLinesOfBusiness(String linesOfBusiness) {
        this.linesOfBusiness = linesOfBusiness;
    }

    public LegalRepresentative getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(LegalRepresentative legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public boolean isEmailOptOut() {
        return emailOptOut;
    }

    public void setEmailOptOut(boolean emailOptOut) {
        this.emailOptOut = emailOptOut;
    }

    public boolean isAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(boolean autoApprove) {
        this.autoApprove = autoApprove;
    }

    public boolean isAutoTransfer() {
        return autoTransfer;
    }

    public void setAutoTransfer(boolean autoTransfer) {
        this.autoTransfer = autoTransfer;
    }

    public String toJson(){
        ObjectMapper mapper = new ObjectMapper();

        try {
            String jsonString = mapper.writeValueAsString(this);

            System.out.println("Json de DigitalAccountRequest: \n" + jsonString);
            return jsonString;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
