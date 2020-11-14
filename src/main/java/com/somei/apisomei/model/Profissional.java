package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.somei.apisomei.util.StringListConverter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;

import static java.util.Map.Entry.comparingByKey;

@Entity
@Table(name = "profissional")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Profissional extends Pessoa implements Serializable {

    @NotBlank
    @CNPJ
    private String cnpj;

    @NotBlank
    private String nomeFantasia;

    @JsonIgnore
    @Convert(converter = StringListConverter.class)
    List<String> portfolio = new ArrayList<>();

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
    private List<Servico> servico;

    private String idNfe;
    private String idAccountJuno;
    private String resourceTokenJuno;


    public String getCnpj() {
        return cnpj;
    }

    @JsonIgnore
    public long getCnpjNumber(){
        String cnpjClean = cnpj.replace(".", "");
        cnpjClean = cnpjClean.replace("-", "");
        cnpjClean = cnpjClean.replace("/", "");

        return Long.parseLong(cnpjClean);
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

    public void setPortfolio(List<String> fotos) {
        this.portfolio = fotos;
    }

    public void addPortfolio(String foto){
        List<String> fotos = new ArrayList<>();
        if(this.portfolio != null)
            fotos = new ArrayList<>(this.getPortfolio());

        fotos.add(foto);
        this.setPortfolio(fotos);
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

    private List<Servico> getOrcamento() {
        return servico;
    }

    public Financeiro getFinanceiro() {
        return financeiro;
    }

    public void setFinanceiro(Financeiro financeiro) {
        this.financeiro = financeiro;
    }

    @JsonIgnore
    public String getIdNfe() {
        return idNfe;
    }

    @JsonIgnore
    public void setIdNfe(String idNfe) {
        this.idNfe = idNfe;
    }

    @JsonIgnore
    public String getIdAccountJuno() {
        return idAccountJuno;
    }

    @JsonIgnore
    public void setIdAccountJuno(String idAccountJuno) {
        this.idAccountJuno = idAccountJuno;
    }

    @JsonIgnore
    public String getResourceTokenJuno() {
        return resourceTokenJuno;
    }

    @JsonIgnore
    public void setResourceTokenJuno(String resourceTokenJuno) {
        this.resourceTokenJuno = resourceTokenJuno;
    }

    @JsonIgnore
    public List<String> getTopServicos(){
        return getTop3Servicos(this.servico);
    }
}
