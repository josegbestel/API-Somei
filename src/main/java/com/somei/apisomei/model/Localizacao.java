package com.somei.apisomei.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "localizacao")
public class Localizacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String cep;

    @NotBlank
    @Size(max = 255)
    private String logradouro;

    @NotNull
    private int numero;

    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    @NotNull
    private String cidade;

    @NotBlank
    private String uf;

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;

//    @OneToMany(mappedBy = "localizacao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Orcamento> orcamentos;
    @OneToOne(mappedBy = "localizacao", fetch = FetchType.LAZY)
    private Orcamento orcamentos;

    @OneToOne(mappedBy = "localizacao", fetch = FetchType.LAZY)
    private Profissional profissional;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public static double distancia(Localizacao localizacao1, Localizacao localizacao2) {

        double lat1, lat2, lon1, lon2;
        lat1 = localizacao1.getLatitude();
        lon1 = localizacao1.getLongitude();
        lat2 = localizacao2.getLatitude();
        lon2 = localizacao2.getLongitude();

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        //transforma em kilometros
        dist = dist * 1.609344;

//        System.out.println(localizacao1.getLogradouro() + " " + localizacao1.getNumero() + " -> " +
//                localizacao2.getLogradouro() + " " + localizacao2.getNumero() + " = " + dist);

        return (dist);
    }

    //Converte graus decimais para radianos
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    //Converte radianos para graus decimais
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
