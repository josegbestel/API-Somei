package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.CategoriaMei;

import java.io.Serializable;

public class CategoriaMeiNovaModel implements Serializable {

    private String cnae;
    private String descricao;
    private String ocupacao;
    private String titulo;

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public CategoriaMei byModel(){
        CategoriaMei categoriaMei = new CategoriaMei();
        categoriaMei.setCnae(this.getCnae());
        categoriaMei.setDescricao(this.getDescricao());
        categoriaMei.setOcupacao(this.getOcupacao());
        categoriaMei.setTitulo(this.getTitulo());

        return categoriaMei;
    }
}
