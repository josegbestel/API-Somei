package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.Avaliacao;

import java.io.Serializable;

public class ComentarioModel implements Serializable {

    private String pessoaNome;
    private String descricao;
    private int rating;

    public String getPessoaNome() {
        return pessoaNome;
    }

    public void setPessoaNome(String pessoaNome) {
        this.pessoaNome = pessoaNome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public static ComentarioModel toModel(Avaliacao avaliacao){
        ComentarioModel comentarioModel = new ComentarioModel();
        comentarioModel.setDescricao(avaliacao.getComentario());
        comentarioModel.setPessoaNome(avaliacao.getCriador().getNome());
        comentarioModel.setRating(avaliacao.getNota());

        return comentarioModel;
    }
}
