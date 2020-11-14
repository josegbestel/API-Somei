package com.somei.apisomei.model.representationModel;

import java.text.DecimalFormat;

public class FinanceiroDepositoSaldoModel {

    private float saldoDisponivel;
    private float saldoALiberar;

    public float getSaldoDisponivel() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        String number = df.format(this.saldoDisponivel);
        number = number.replaceAll("\\.", "");
        number = number.replaceAll(",", ".");

        return Float.parseFloat(number);
    }

    public void setSaldoDisponivel(float saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    public float getSaldoALiberar() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        String number = df.format(this.saldoALiberar);
        number = number.replaceAll("\\.", "");
        number = number.replaceAll(",", ".");

        return Float.parseFloat(number);
    }

    public void setSaldoALiberar(float saldoALiberar) {
        this.saldoALiberar = saldoALiberar;
    }
}
