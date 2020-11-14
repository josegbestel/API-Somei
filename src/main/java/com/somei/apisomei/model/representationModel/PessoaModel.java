package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.util.CustomDate;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PessoaModel{

    @Id
    private long id;

    private String nome;
    private LocalDate dtNascimento;

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

    public CustomDate getDtNascimento() {
        return CustomDate.byLocalDate(dtNascimento);
    }

    public void setDtNascimento(CustomDate dtNascimento) {
        this.dtNascimento = dtNascimento.toLocalDate();
    }

    public String getTelefone() {
        return telefone.isEmpty() ? null : telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("-", "");;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
