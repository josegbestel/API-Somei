package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "profissional_id", nullable = true)
    private Profissional profissional;

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

    //TODO: AUTORIA
    private float valorMinimo;
    private float valorMaximo;

    public float getValorMinimo() {
        return valorMinimo;
    }
    public void setValorMinimo(float valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public float getValorMaximo() {
        return valorMaximo;
    }
    public void setValorMaximo(float valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public float getMediaValor(){
        return (valorMaximo+valorMinimo)/2;
    }

    // ==============================

    @OneToMany(mappedBy = "orcamento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes = new ArrayList<>();

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

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
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
        List<RespostaOrcamento> respondidas = new ArrayList<>();

        for(RespostaOrcamento r : this.respostas){
            if(r.getDtResposta() != null){
                respondidas.add(r);
            }
        }

        return respondidas.size() > 0 ? respostas : null;
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
        System.out.println(this.getRespostas());
        System.out.println(this.getRespostas().size());
        for(RespostaOrcamento resposta : this.getRespostas()){
            System.out.println("orcamento_resposta_id (" + resposta.getId() + ") = " + id);
            if(id == resposta.getId())
                return true;
        }
        return false;
    }

    public void setRespostaEscolhida(long idResposta){
        for(RespostaOrcamento r : this.respostas){
            if(r.getId() == idResposta){
                this.profissional = r.getProfissional();
            }
        }
    }

    public RespostaOrcamento getRespostaEscolhida(){
        for(RespostaOrcamento resposta : this.respostas){
            if(resposta.isEscolhida())
                return resposta;
        }
        return null;
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

    @JsonIgnore
    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public void addAvaliacao(Avaliacao avaliacao){
        this.avaliacoes.add(avaliacao);
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

        orcamento.setValorMaximo(orcamentoNovoModel.getValorMaximo());
        orcamento.setValorMinimo(orcamentoNovoModel.getValorMinimo());

        System.out.println("minimo: " + orcamento.valorMinimo);
        System.out.println("maximo: " + orcamento.valorMaximo);


        return orcamento;
    }

    public Avaliacao getAvaliacaoProfissional() {
        for(Avaliacao a : this.avaliacoes){
            if(a.getDestinatario().getId() == this.profissional.getId())
                return a;
        }
        return null;
    }

    public Avaliacao getAvaliacaoSolicitante(){
        for(Avaliacao a : this.avaliacoes){
            if(a.getCriador().getId() == this.solicitante.getId())
                return a;
        }
        return null;
    }
}
