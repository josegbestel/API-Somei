package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "solicitante")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Solicitante extends Pessoa implements Serializable {


    @OneToMany(mappedBy = "solicitante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Servico> servico;

    private List<Servico> getOrcamento() {
        return servico;
    }

    @JsonIgnore
    public List<String> getTopServicos(){
        return getTop3Servicos(this.servico);
    }
}
