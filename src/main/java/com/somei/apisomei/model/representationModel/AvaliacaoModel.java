package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.Avaliacao;
import com.somei.apisomei.model.Servico;
import com.somei.apisomei.model.Pessoa;
import com.somei.apisomei.util.CustomDateTime;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AvaliacaoModel implements Serializable {

    private long idOrcamento;
    private int nota;
    private String comentario;

    public long getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(long idOrcamento) {
        this.idOrcamento = idOrcamento;
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
}
