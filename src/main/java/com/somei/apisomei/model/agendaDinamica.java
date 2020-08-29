package com.somei.apisomei.model;

import com.somei.apisomei.model.enums.DiaSemana;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "agenda_dinamica")
public class agendaDinamica extends Agenda implements Serializable {

    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }
}
