package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.Avaliacao;
import com.somei.apisomei.model.Solicitante;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class SolicitantePerfilModel {

    private long id;
    private String nome;
    private int rating;
    private List<ComentarioModel> comentarios = new ArrayList<>();
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

    public void setComentarios(List<ComentarioModel> comentarios) {
        this.comentarios = comentarios;
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

    public static SolicitantePerfilModel toModel(Solicitante solicitante){
        SolicitantePerfilModel perfilModel = new SolicitantePerfilModel();
        perfilModel.setId(solicitante.getId());
        perfilModel.setNome(solicitante.getNome());
        perfilModel.setRating(solicitante.getRating());
        perfilModel.setServicos(solicitante.getTopServicos());
        solicitante.getAvaliacoesRecebidas().forEach(a -> perfilModel.addComentario(a));

        return perfilModel;
    }
}
