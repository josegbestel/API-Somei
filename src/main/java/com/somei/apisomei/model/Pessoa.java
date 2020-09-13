package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.somei.apisomei.model.enums.AuthType;
import com.somei.apisomei.model.enums.StatusOrcamento;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toMap;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @Size(max = 11, min=10)
    private String telefone;

    @NotBlank
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
    @Size(min=8)
    private String senha;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private AuthType authType;

    @OneToMany(mappedBy = "criador" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoesFeitas;

    @OneToMany(mappedBy = "destinatario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoesRecebidas = new ArrayList<>();


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

    public int getRating(){
        if(this.avaliacoesRecebidas.size() == 0 || this.avaliacoesRecebidas == null)
            return -1;

        List<Avaliacao> avaliacoes = this.avaliacoesRecebidas;
        float somaNotas = 0;
        for(Avaliacao avaliacao : avaliacoes){
            somaNotas = somaNotas + avaliacao.getNota();
        }

        float rating = somaNotas/avaliacoes.size();
        return Math.round(rating);
    }

    @JsonIgnore
    public List<Avaliacao> getAvaliacoesRecebidas() {
        return avaliacoesRecebidas;
    }

    public void setAvaliacoesRecebidas(List<Avaliacao> avaliacoesRecebidas) {
        this.avaliacoesRecebidas = avaliacoesRecebidas;
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

    @JsonIgnore
    protected List<String> getTop3Servicos(List<Orcamento> orcamentos){
        HashMap<String, Integer> servicos = new HashMap<>();

        //Pega todos os serviços que fez e adiciona no map
        for (Orcamento o : orcamentos) {
            //TODO: Habilitar esse if quando tiver o fluxo completo (2a parte)
//            if(o.getStatus() == StatusOrcamento.FINALIZADO){
            if(servicos.containsKey(o.getServico())){
                servicos.put(o.getServico(), servicos.get(o.getServico())+1);
            }else{
                servicos.put(o.getServico(), 1);
            }
//            }
        }

        //Se possui pelo menos 1
        if(servicos.size() > 0){
            //Ordena o map
            Map<String, Integer> mapOrdenada = servicos.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            //Transforma o map num array com as keys
            List<String> servicosOrdenados = new ArrayList<>();
            mapOrdenada.entrySet().forEach((x) -> servicosOrdenados.add(x.getKey()));

            //Obter os 3 primeiros
            List<String> servicosTop3 = new ArrayList<>();
            int max = servicosOrdenados.size() >= 3 ? 3 : servicosOrdenados.size();
            for(int i = 1; i <= max; i++){
                int pos = servicosOrdenados.size()-i;
                servicosTop3.add(servicosOrdenados.get(pos));
            }

            return servicosTop3;
        }

        return null;
    }
}
