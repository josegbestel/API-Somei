package com.somei.apisomei.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonObject;
import com.somei.apisomei.model.Localizacao;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityNfeDTO implements Serializable {

    public String code;
    public String name;

    public CityNfeDTO() {
    }

    public CityNfeDTO(Localizacao localizacao) {
        this.code = "4106902";
        this.name = localizacao.getCidade();
    }

    public JsonObject toJson(){
        JsonObject city = new JsonObject();

        city.addProperty("code", this.getCode());
        city.addProperty("name", this.getName());

        return city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
