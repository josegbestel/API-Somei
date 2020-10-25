package com.somei.apisomei.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompaniesListNfeDTO implements Serializable {

    public int totalResults;
    public List<CompaniesNfeDTO> companies;

    public CompaniesListNfeDTO() {
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<CompaniesNfeDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompaniesNfeDTO> companies) {
        this.companies = companies;
    }
}
