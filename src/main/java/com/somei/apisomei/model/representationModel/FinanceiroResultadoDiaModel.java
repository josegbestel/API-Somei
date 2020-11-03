package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.DepositoBancario;
import com.somei.apisomei.model.Profissional;

import java.text.DecimalFormat;
import java.util.List;

public class FinanceiroResultadoDiaModel {

    private int dia;
    private float valor;

    public FinanceiroResultadoDiaModel() {
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
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
