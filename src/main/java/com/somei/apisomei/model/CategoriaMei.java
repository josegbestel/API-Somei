package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categoria_mei")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CategoriaMei implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String cnae;

    @NotBlank
    private String descricao;

    @NotBlank
    private String ocupacao;

    @NotBlank
    private String titulo;


    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Profissional> profissionais;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private List<Orcamento> orcamentos;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @JsonIgnore
    public List<Profissional> getProfissional() {
        return profissionais;
    }

    public void setProfissional(List<Profissional> profissionais) {
        this.profissionais = profissionais;
    }
}
