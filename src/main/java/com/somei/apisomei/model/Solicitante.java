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

    @NotNull
    @NotBlank
    @Column(unique = true)
    @CPF(message = "CPF invalido")
    private String cpf;

    @OneToMany(mappedBy = "solicitante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cartao> cartoes;

    @OneToMany(mappedBy = "solicitante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Servico> servico;

    public String getCpf() {
        return cpf;
    }

    @JsonIgnore
    public long getCpfNumber(){
        String cpfNumber = cpf.replace(".", "");
        cpfNumber = cpfNumber.replace("-", "");

        return Long.parseLong(cpfNumber);
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    private List<Servico> getOrcamento() {
        return servico;
    }

    @JsonIgnore
    public List<String> getTopServicos(){
        return getTop3Servicos(this.servico);
    }
}
