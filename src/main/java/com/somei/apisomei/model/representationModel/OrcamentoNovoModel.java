package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.*;
import com.somei.apisomei.model.enums.StatusOrcamento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class OrcamentoNovoModel implements Serializable {

    @NotNull
    private long solicitanteId;

    @NotNull
    private long categoriaMeiId;

    @NotBlank
    private String servico;
    private List<Agenda> agendas;
    private List<String> fotos;
    private Localizacao localizacao;

    public long getSolicitanteId() {
        return solicitanteId;
    }

    public void setSolicitanteId(long solicitanteId) {
        this.solicitanteId = solicitanteId;
    }

    public long getCategoriaMeiId() {
        return categoriaMeiId;
    }

    public void setCategoriaMeiId(long categoriaId) {
        this.categoriaMeiId = categoriaId;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
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

    public Orcamento byModel(Solicitante solicitante, CategoriaMei categoria){
        Orcamento orcamento = new Orcamento();
        orcamento.setLocalizacao(this.localizacao);
//        orcamento.setAgendasEspecificas(this.agendasEspecificas);
//        orcamento.setAgendasDinamicas(this.agendasDinamicas);
        orcamento.setFotos(this.fotos);
        orcamento.setServico(this.servico);
        orcamento.setSolicitante(solicitante);
        orcamento.setCategoria(categoria);

        return orcamento;

    }
}
