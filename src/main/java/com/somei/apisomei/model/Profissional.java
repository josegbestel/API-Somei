package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.somei.apisomei.model.enums.StatusOrcamento;
import com.somei.apisomei.util.StringListConverter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.*;

@Entity
@Table(name = "profissional")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Profissional extends Pessoa implements Serializable {

    @NotBlank
    @NotNull
    @CNPJ
    private String cnpj;

    @NotBlank
    private String nomeFantasia;

    @JsonIgnore
    @Convert(converter = StringListConverter.class)
    List<String> portfolio;

    @OneToOne(mappedBy = "profissional")
    private Financeiro financeiro;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaMei categoria;

    @OneToMany(mappedBy = "profissional", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RespostaOrcamento> respostasOrcamento;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Localizacao localizacao;

    @OneToMany(mappedBy = "profissional", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Orcamento> orcamento;


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public List<String> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(List<String> portfolio) {
        this.portfolio = portfolio;
    }

    public CategoriaMei getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaMei categoria) {
        this.categoria = categoria;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    private List<Orcamento> getOrcamento() {
        return orcamento;
    }

    @JsonIgnore
    public List<String> getTopServicos(){
        return getTop3Servicos(this.orcamento);
    }
}
