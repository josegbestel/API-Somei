package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.util.CustomDate;
import com.somei.apisomei.util.CustomDateTime;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CartaoModel implements Serializable {

    @NotBlank
    @CreditCardNumber
    private String numeroCartao;

    private LocalDate validade;

    private int cvv;

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
}
