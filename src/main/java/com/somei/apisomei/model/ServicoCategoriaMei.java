package com.somei.apisomei.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "servico_categoria_mei")
public class ServicoCategoriaMei implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotBlank
    @NotNull
    private String descricao;

    @NotNull
    private boolean validado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaMei categoria;

    @OneToMany(mappedBy = "servicoCategoriaMei", fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private List<Orcamento> orcamentos;


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public CategoriaMei getCategoriaMei() {
        return categoria;
    }

    public void setCategoriaMei(CategoriaMei categoria) {
        this.categoria = categoria;
    }
}
