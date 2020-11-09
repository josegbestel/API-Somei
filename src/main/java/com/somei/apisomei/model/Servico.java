package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.somei.apisomei.model.enums.StatusServico;
import com.somei.apisomei.model.representationModel.ServicoNovoModel;
import com.somei.apisomei.util.StringListConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servico")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Servico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "solicitante_id")
    private Solicitante solicitante;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "profissional_id", nullable = true)
    private Profissional profissional;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoriaMei_id")
    private CategoriaMei categoria;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Localizacao localizacao;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE})
    private List<Agenda> agendas = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE})
    private List<RespostaOrcamento> respostas = new ArrayList<>();

    @Convert(converter = StringListConverter.class)
    private List<String> fotos;

    @Enumerated(EnumType.STRING)
    private StatusServico status;

    private LocalDateTime dtInativo;
    private LocalDateTime dtConcluido;

    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    //Informações que o profissional insere no final
    private float custoExecucao;
    private String codigoServicoMunicipal;

    //TODO: Relacionar NF e Pagamento
    @OneToOne(mappedBy = "servico")
    private NotaFiscal notaFiscal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public StatusServico getStatus() {
        return status;
    }

    public void setStatus(StatusServico status) {
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
        this.status = StatusServico.CANCELADO;
    }

    public LocalDateTime getDtConcluido() {
        return dtConcluido;
    }

    public void setDtConcluido(LocalDateTime dtConcluido) {
        this.dtConcluido = dtConcluido;
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

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    static public Servico byModel(ServicoNovoModel servicoNovoModel, Solicitante solicitante, CategoriaMei categoria){
        Servico servico = new Servico();
        servico.setId(0);
        servico.setLocalizacao(servicoNovoModel.getLocalizacao());
        servico.setFotos(servicoNovoModel.getFotos());
        servico.setDescricao(servicoNovoModel.getDescricao());
        servico.setSolicitante(solicitante);
        servico.setCategoria(categoria);

        return servico;
    }

    public Avaliacao getAvaliacaoProfissional() {
        for(Avaliacao a : this.avaliacoes){
            if(a.getCriador().getId() == this.profissional.getId())
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

    public float getCustoExecucao() {
        return custoExecucao;
    }

    public void setCustoExecucao(float custoExecucao) {
        this.custoExecucao = custoExecucao;
    }

    public String getCodigoServicoMunicipal() {
        return codigoServicoMunicipal;
    }

    public void setCodigoServicoMunicipal(String codigoServicoMunicipal) {
        this.codigoServicoMunicipal = codigoServicoMunicipal;
    }

    public double getValorContratado(){
        if(this.getRespostaEscolhida() != null)
            return this.getRespostaEscolhida().getValor();

        return 0.0;
    }
}
