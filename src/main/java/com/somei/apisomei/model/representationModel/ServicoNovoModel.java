package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class ServicoNovoModel implements Serializable {

    @NotNull
    private long solicitanteId;

    @NotBlank
    private String categoriaMeiTitulo;

    @NotBlank
    private String descricao;
    private List<Agenda> agendas;
    private List<String> fotos;
    private Localizacao localizacao;

    public long getSolicitanteId() {
        return solicitanteId;
    }

    public void setSolicitanteId(long solicitanteId) {
        this.solicitanteId = solicitanteId;
    }

    public String getCategoriaMeiTitulo() {
        return categoriaMeiTitulo;
    }

    public void setCategoriaMeiTitulo(String categoriaMeiTitulo) {
        this.categoriaMeiTitulo = categoriaMeiTitulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Agenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(List<Agenda> agendas) {
        this.agendas = agendas;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public Servico byModel(Solicitante solicitante, CategoriaMei categoria){
        Servico servico = new Servico();
        servico.setLocalizacao(this.localizacao);
//        orcamento.setAgendasEspecificas(this.agendasEspecificas);
//        orcamento.setAgendasDinamicas(this.agendasDinamicas);
        servico.setFotos(this.fotos);
        servico.setDescricao(this.descricao);
        servico.setSolicitante(solicitante);
        servico.setCategoria(categoria);
        return servico;

    }
}
