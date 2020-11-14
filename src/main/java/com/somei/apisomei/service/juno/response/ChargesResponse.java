package com.somei.apisomei.service.juno.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargesResponse implements Serializable {

    @JsonProperty(value = "_embedded")
    private EmbeddedResponse embedded;

    public ChargesResponse() {
    }

    public EmbeddedResponse getEmbedded() {
        return embedded;
    }

    public void setEmbedded(EmbeddedResponse embedded) {
        this.embedded = embedded;
    }
}
