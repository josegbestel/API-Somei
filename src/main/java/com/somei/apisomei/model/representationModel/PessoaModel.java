package com.somei.apisomei.model.representationModel;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PessoaModel{

    @Id
    private long id;

    private String nome;
    private Integer anoNascimento;

    @NotBlank
    @Size(max = 11, min=10)
    private String telefone;

    private String avatar;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getTelefone() {
        return telefone.isEmpty() ? null : telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
