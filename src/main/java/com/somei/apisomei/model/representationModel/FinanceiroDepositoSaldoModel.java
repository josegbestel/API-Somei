package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.util.DecimalFormatUtil;

import java.text.DecimalFormat;

public class FinanceiroDepositoSaldoModel {

    private float saldoDisponivel;
    private float saldoALiberar;

    public float getSaldoDisponivel() {
        return DecimalFormatUtil.format(this.saldoDisponivel);
    }

    public void setSaldoDisponivel(float saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    public float getSaldoALiberar() {
        return DecimalFormatUtil.format(this.saldoALiberar);
    }

    public void setSaldoALiberar(float saldoALiberar) {
        this.saldoALiberar = saldoALiberar;
    }
}
