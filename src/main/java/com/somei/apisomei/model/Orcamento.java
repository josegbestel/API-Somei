package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.somei.apisomei.model.enums.StatusOrcamento;
import com.somei.apisomei.model.representationModel.OrcamentoNovoModel;
import com.somei.apisomei.util.StringListConverter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orcamento")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Orcamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "solicitante_id")
    private Solicitante solicitante;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoriaMei_id")
    private CategoriaMei categoria;

    @NotBlank
    private String servico;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Localizacao localizacao;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE})
    private List<Agenda> agendas = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE})
    private List<RespostaOrcamento> respostas = new ArrayList<>();

    @Convert(converter = StringListConverter.class)
    private List<String> fotos;

    @Enumerated(EnumType.STRING)
    private StatusOrcamento status;

    private LocalDateTime dtInativo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public CategoriaMei getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaMei categoria) {
        this.categoria = categoria;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public List<Agenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(List<Agenda> agendas) {
        agendas.forEach(a -> a.setOrcamento(this));
        this.agendas = agendas;
    }

    public void addAgendas(List<Agenda> agendas){
        agendas.forEach(a -> a.setOrcamento(this));
        agendas.forEach(a -> this.agendas.add(a));
    }

    public List<RespostaOrcamento> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<RespostaOrcamento> respostas) {
        respostas.forEach(r -> r.setOrcamento(this));
        this.respostas = respostas;
    }

    public void addRespostas(List<RespostaOrcamento> respostas){
        respostas.forEach(r -> r.setOrcamento(this));
        respostas.forEach(r -> this.respostas.add(r));
    }

    public void addResposta(RespostaOrcamento resposta){
        resposta.setOrcamento(this);
        this.respostas.add(resposta);
    }

    public boolean existsResposta(long id){
        for(RespostaOrcamento resposta : this.respostas){
            if(id == resposta.getId())
                return true;
        }
        return false;
    }

    public void setRespostaEscolhida(long idResposta){
        this.respostas.forEach(r -> r.setEscolhida(r.getId() == idResposta ? true : false));
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public StatusOrcamento getStatus() {
        return status;
    }

    public void setStatus(StatusOrcamento status) {
        this.status = status;
    }

    public LocalDateTime getDtInativo() {
        return dtInativo;
    }

    public void setDtInativo(LocalDateTime dtInativo) {
        this.dtInativo = dtInativo;
    }

    public void inativar(){
        this.dtInativo = LocalDateTime.now();
        this.status = StatusOrcamento.CANCELADO;
    }

    static public Orcamento byModel(OrcamentoNovoModel orcamentoNovoModel,
                                    Solicitante solicitante,
                                    CategoriaMei categoria){
        Orcamento orcamento = new Orcamento();
        orcamento.setId(0);
        orcamento.setLocalizacao(orcamentoNovoModel.getLocalizacao());
        orcamento.setFotos(orcamentoNovoModel.getFotos());
        orcamento.setServico(orcamentoNovoModel.getServico());
        orcamento.setSolicitante(solicitante);
        orcamento.setCategoria(categoria);

        return orcamento;
    }
}
