package com.somei.apisomei.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "commentary")
public class Commentary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nameUser;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "note_id", nullable = false)
    private Note note;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // NÃO EXISTE O GET POIS SE NÃO FICA LOOF INFINITO
//    public Note getNote() {
//        return note;
//    }

    public void setNote(Note note) {
        this.note = note;
    }
}
