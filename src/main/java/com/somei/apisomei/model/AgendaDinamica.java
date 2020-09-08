package com.somei.apisomei.model;

import com.somei.apisomei.model.enums.DiaSemana;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "agenda_dinamica")
public class AgendaDinamica extends Agenda implements Serializable {

    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "orcamento_id")
    private Orcamento orcamento;

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    private Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

//    public long getOrcamentoId(){
//        return getOrcamento().getId();
//    }
}
