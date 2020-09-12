package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.Avaliacao;
import com.somei.apisomei.model.Orcamento;
import com.somei.apisomei.model.Pessoa;
import com.somei.apisomei.util.CustomDateTime;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AvaliacaoModel implements Serializable {

    private long idCriaddor;
    private long idDestinatario;
    private long idOrcamento;
    private int nota;
    private String comentario;

    public long getIdCriaddor() {
        return idCriaddor;
    }

    public void setIdCriaddor(long idCriaddor) {
        this.idCriaddor = idCriaddor;
    }

    public long getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(long idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Avaliacao byModel(Pessoa criador, Pessoa destinatario, Orcamento orcamento){
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setCriador(criador);
        avaliacao.setDestinatario(destinatario);
        avaliacao.setOrcamento(orcamento);
        avaliacao.setNota(this.nota);
        avaliacao.setComentario(this.comentario);
        avaliacao.setNota(this.nota);
        avaliacao.setDtCriacao(CustomDateTime.byLocalDateTime(LocalDateTime.now()));

        return avaliacao;
    }
}
