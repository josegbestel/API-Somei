package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.somei.apisomei.model.enums.DiaSemana;
import com.somei.apisomei.util.CustomDate;
import com.somei.apisomei.util.CustomTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name = "agenda")
@JsonInclude(JsonInclude.Include.NON_NULL)
//@Inheritance(strategy = InheritanceType.JOINED)
public class Agenda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private LocalTime horaInicio;

    @NotNull
    private LocalTime horaFinal;

    private LocalDate data;
    private DiaSemana diaSemana;
    private boolean dinamica;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "orcamento_id")
    private Servico servico;

    @OneToMany(mappedBy = "agenda")
    private List<RespostaOrcamento> respostas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CustomTime getHoraInicio() {
        return CustomTime.byLocalTime(horaInicio);
    }

    public void setHoraInicio(CustomTime horaInicio) {
        this.horaInicio = horaInicio.toLocalTime();
    }

    public CustomTime getHoraFinal() {
        return CustomTime.byLocalTime(horaFinal);
    }

    public void setHoraFinal(CustomTime horaFinal) {
        this.horaFinal = horaFinal.toLocalTime();
    }

    public CustomDate getData() {
        return CustomDate.byLocalDate(data);
    }

    public void setData(CustomDate data) {
        this.data = data.toLocalDate();
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public boolean isDinamica() {
        return dinamica;
    }

    public void setDinamica(boolean dinamica) {
        this.dinamica = dinamica;
    }

    private Servico getOrcamento() {
        return servico;
    }

    public void setOrcamento(Servico servico) {
        this.servico = servico;
    }
}
