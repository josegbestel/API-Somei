package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.somei.apisomei.model.enums.AuthType;
import com.somei.apisomei.util.View;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @NotNull
    @Size(max = 255)
    private String nome;

    @Column(name = "ano_nascimento")
    private int anoNascimento;

    @NotBlank
    @NotNull
    @Size(max = 11, min=10)
    private String telefone;

    @NotBlank
    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    private String avatar;

    @JsonIgnore
    private boolean ativo;

    @JsonIgnore
    private LocalDateTime dtInativo;

    private LocalDateTime dtAtivo;

    @NotBlank
    @NotNull
    @Size(min=8)
    private String senha;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private AuthType authType;

    @JsonIgnore
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Localizacao> localizacoes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @JsonProperty(value = "senha")
    @JsonIgnore
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDtInativo() {
        return dtInativo;
    }

    public void setDtInativo(LocalDateTime dtInativo) {
        this.dtInativo = dtInativo;
    }

    public Set<Localizacao> getLocalizacoes() {
        return localizacoes;
    }

    public void setLocalizacoes(Set<Localizacao> localizacoes) {
        this.localizacoes = localizacoes;
    }

    public static boolean validateUser(UserDetails userDetails, Pessoa pessoa){
        if(userDetails.getPassword().equals(pessoa.getSenha()) &&
        userDetails.getUsername().equals(pessoa.getEmail())){
            return true;
        }
        return false;
    }

    public LocalDateTime getDtAtivo() {
        return dtAtivo;
    }

    @JsonIgnore
    public void setDtAtivo(LocalDateTime dtAtivo) {
        this.dtAtivo = dtAtivo;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;
        Pessoa pessoa = (Pessoa) o;
        return id == pessoa.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
