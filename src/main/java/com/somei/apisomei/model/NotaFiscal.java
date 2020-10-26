package com.somei.apisomei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.somei.apisomei.model.dto.ServiceInvoiceNfeDTO;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "nota_fiscal")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NotaFiscal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String idNfe;
    private String dtGeracao;
    private Long CnpjEmitente;
    private Long cpfTomador;
    private double numero;
    private String emailTomador;
    private Double valor;

    @OneToOne
    private Servico servico;

    public NotaFiscal() {
    }

    public NotaFiscal(ServiceInvoiceNfeDTO serviceInvoice){
        this.idNfe = serviceInvoice.getId();
        this.dtGeracao = serviceInvoice.getCreatedOn();
        CnpjEmitente = serviceInvoice.getProvider().getFederalTaxNumber();
        this.cpfTomador = serviceInvoice.getBorrower().getFederalTaxNumber();
        this.emailTomador = serviceInvoice.getBorrower().getEmail();
        this.numero = serviceInvoice.getNumber();
        this.valor = serviceInvoice.getServicesAmount();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdNfe() {
        return idNfe;
    }

    public void setIdNfe(String idNfe) {
        this.idNfe = idNfe;
    }

    public String getDtGeracao() {
        return dtGeracao;
    }

    public void setDtGeracao(String dtGeracao) {
        this.dtGeracao = dtGeracao;
    }

    public Long getCnpjEmitente() {
        return CnpjEmitente;
    }

    public void setCnpjEmitente(Long cnpjEmitente) {
        CnpjEmitente = cnpjEmitente;
    }

    public Long getCpfTomador() {
        return cpfTomador;
    }

    public void setCpfTomador(Long cpfTomador) {
        this.cpfTomador = cpfTomador;
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public String getEmailTomador() {
        return emailTomador;
    }

    public void setEmailTomador(String emailTomador) {
        this.emailTomador = emailTomador;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @JsonIgnore
    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
