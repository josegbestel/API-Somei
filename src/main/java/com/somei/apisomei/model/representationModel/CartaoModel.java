package com.somei.apisomei.model.representationModel;

import java.io.Serializable;

public class CartaoModel implements Serializable {

    private String hashCartao;

    public String getHashCartao() {
        return hashCartao;
    }

    public void setHashCartao(String hashCartao) {
        this.hashCartao = hashCartao;
    }
}
