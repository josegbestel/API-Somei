package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.DepositoBancario;
import com.somei.apisomei.util.DecimalFormatUtil;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FinanceiroDepositoModel {

    private LocalDate dia;
    private float valor;

    public FinanceiroDepositoModel() {
    }

    public FinanceiroDepositoModel(DepositoBancario proximoDeposito) {
        this.dia = proximoDeposito.getDtPrevista();
        this.valor = proximoDeposito.getValor();
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public float getValor() {
        return DecimalFormatUtil.format(this.valor);
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
