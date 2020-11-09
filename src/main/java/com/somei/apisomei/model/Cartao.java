package com.somei.apisomei.model;

import com.somei.apisomei.util.CustomDate;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cartao")
public class Cartao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //TODO: Verificar qual erro da e criar uma msg em messages.properties
    @NotBlank
    @CreditCardNumber
    private String numeroCartao;

    @NotNull
    private LocalDate validade;

    @NotNull
    private int cvv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "solicitante_id", nullable = false)
    private Solicitante solicitante;

    private String keyGateway;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public CustomDate getValidade() {
        return CustomDate.byLocalDate(this.validade);
    }

    public void setValidade(CustomDate validade) {
        this.validade = validade.toLocalDate();
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getKeyGateway() {
        return keyGateway;
    }

    public void setKeyGateway(String keyGateway) {
        this.keyGateway = keyGateway;
    }
}
