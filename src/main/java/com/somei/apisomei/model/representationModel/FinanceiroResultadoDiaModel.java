package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.DepositoBancario;
import com.somei.apisomei.model.Profissional;
import com.somei.apisomei.util.DecimalFormatUtil;

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

        return DecimalFormatUtil.format(this.valor);
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
