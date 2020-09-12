package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.Avaliacao;
import com.somei.apisomei.model.Profissional;
import com.somei.apisomei.util.StringListConverter;

import javax.persistence.Convert;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ProfissionalPerfilModel implements Serializable {

    private long id;
    private String nome;
    private int rating;
    private List<ComentarioModel> comentarios = new ArrayList<>();
    private List<String> portfolio;

    @Convert(converter = StringListConverter.class)
    private List<String> servicos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<ComentarioModel> getComentarios() {
        return comentarios;
    }

    public void addComentario(Avaliacao avaliacao){
        this.comentarios.add(ComentarioModel.toModel(avaliacao));
    }

    public List<String> getServicos() {
        return servicos;
    }

    public void setServicos(List<String> servicos) {
        this.servicos = servicos;
    }

    public List<String> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(List<String> portfolio) {
        this.portfolio = portfolio;
    }

    public static ProfissionalPerfilModel toModel(Profissional profissional){
        ProfissionalPerfilModel perfilModel = new ProfissionalPerfilModel();
        perfilModel.setId(profissional.getId());
        perfilModel.setNome(profissional.getNomeFantasia());
        perfilModel.setRating(profissional.getRating());
        perfilModel.setServicos(profissional.getTopServicos());
        perfilModel.setPortfolio(profissional.getPortfolio());

        profissional.getAvaliacoesRecebidas().forEach(a -> perfilModel.addComentario(a));

        return perfilModel;
    }
}
