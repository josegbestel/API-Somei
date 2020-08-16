package com.somei.apisomei.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "profissao")
public class Profissao implements Serializable {

    @Id
    private String id;
    private String categoria;
    private String descricao;
}
