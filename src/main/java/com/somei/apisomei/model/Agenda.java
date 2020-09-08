package com.somei.apisomei.model;

import com.somei.apisomei.util.CustomTime;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;


@Entity
@Table(name = "agenda")
@Inheritance(strategy = InheritanceType.JOINED)
public class Agenda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalTime horaInicio;
    private LocalTime horaFinal;

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
}
