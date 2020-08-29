package com.somei.apisomei.model;

import com.somei.apisomei.model.enums.StatusOrcamento;
import com.somei.apisomei.util.StringListConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orcamento")
public class Orcamento {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "solicitante_id")
    private Solicitante solicitante;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ServicoCategoriaMei_id")
    private ServicoCategoriaMei servicoCategoriaMei;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;

    @OneToMany(mappedBy = "orcamento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Agenda> agendas;

    @OneToMany(mappedBy = "orcamento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RespostaOrcamento> respostas;

    @Convert(converter = StringListConverter.class)
    private List<String> fotos;

    @Enumerated(EnumType.STRING)
    private StatusOrcamento status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public ServicoCategoriaMei getServicoCategoriaMei() {
        return servicoCategoriaMei;
    }

    public void setServicoCategoriaMei(ServicoCategoriaMei servicoCategoriaMei) {
        this.servicoCategoriaMei = servicoCategoriaMei;
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
        this.agendas = agendas;
    }

    public List<RespostaOrcamento> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<RespostaOrcamento> respostas) {
        this.respostas = respostas;
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
}
