package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.DepositoBancario;

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
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        String number = df.format(this.valor);
        number  = number.replaceAll("\\.", "");
        number  = number.replace(",", ".");

        return Float.parseFloat(number);
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
