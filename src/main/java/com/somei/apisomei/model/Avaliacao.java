package com.somei.apisomei.model;

import com.somei.apisomei.util.CustomDateTime;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacao")
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Range(min = 1, max = 5)
    private int nota;

    @NotBlank
    private String comentario;

    @NotNull
    private LocalDateTime dtCriacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "criador_id", nullable = false)
    Pessoa criador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Pessoa destinatario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orcamento_id", nullable = false)
    private Servico servico;

    public Avaliacao() {
    }

    public Avaliacao(Pessoa criador, Pessoa destinatario, Servico servico, int nota, String comentario){
        this.criador = criador;
        this.destinatario = destinatario;
        this.setServico(servico);
        this.nota = nota;
        this.comentario = comentario;
        this.dtCriacao = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public CustomDateTime getDtCriacao() {
        return CustomDateTime.byLocalDateTime(dtCriacao);
    }

    public void setDtCriacao(CustomDateTime dtCriacao) {
        this.dtCriacao = dtCriacao.toLocalDateTime();
    }

    public Pessoa getCriador() {
        return criador;
    }

    public void setCriador(Pessoa criador) {
        this.criador = criador;
    }

    public Pessoa getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Pessoa destinatario) {
        this.destinatario = destinatario;
    }

    private Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
