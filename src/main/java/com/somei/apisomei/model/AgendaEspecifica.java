package com.somei.apisomei.model;

import com.somei.apisomei.util.CustomDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "agenda_especifica")
public class AgendaEspecifica extends Agenda implements Serializable {

    private LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "orcamento_id")
    private Orcamento orcamento;

    public CustomDate getData() {
        return CustomDate.byLocalDate(data);
    }

    public void setData(CustomDate data) {
        this.data = data.toLocalDate();
    }

    private Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

//    public long getOrcamentoId(){
//        return orcamento.getId();
//    }
}
