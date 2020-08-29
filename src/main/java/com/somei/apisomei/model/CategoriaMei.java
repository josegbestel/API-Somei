package com.somei.apisomei.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categoria_mei")
public class CategoriaMei implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotBlank
    @NotNull
    private String categoria;

    @NotBlank
    @NotNull
    private String descricao;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Profissional> profissionais;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private List<Orcamento> orcamentos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Profissional> getProfissional() {
        return profissionais;
    }

    public void setProfissional(List<Profissional> profissionais) {
        this.profissionais = profissionais;
    }
}
