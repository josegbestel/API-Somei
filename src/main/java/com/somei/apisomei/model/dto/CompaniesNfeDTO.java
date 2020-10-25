package com.somei.apisomei.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompaniesNfeDTO implements Serializable {

    public CompanyNfeDTO companies;

    public CompaniesNfeDTO() {
    }

    public CompanyNfeDTO getCompanies() {
        return companies;
    }

    public void setCompanies(CompanyNfeDTO companies) {
        this.companies = companies;
    }

    public void print(){
        CompanyNfeDTO c = this.companies;
        System.out.println("company " + c.getName() + " (" + c.getId() + ")");
    }
}
