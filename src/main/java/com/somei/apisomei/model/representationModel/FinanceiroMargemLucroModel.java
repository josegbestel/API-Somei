package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.Servico;
import com.somei.apisomei.model.enums.StatusServico;
import com.somei.apisomei.util.DecimalFormatUtil;

import java.text.DecimalFormat;
import java.util.List;

public class FinanceiroMargemLucroModel {

    private double ganhos;
    private double gastos;
    private float porcentagem;

    public FinanceiroMargemLucroModel() {
    }

    public FinanceiroMargemLucroModel(List<Servico> servicos) {
        this.ganhos = 0;
        this.gastos = 0;

        for (Servico s : servicos) {
            if(s.getStatus() == StatusServico.FINALIZADO){
                this.ganhos += s.getValorContratado();
                this.gastos += s.getCustoExecucao();
            }
        }
    }

    public double getGanhos() {
        return DecimalFormatUtil.format(this.ganhos);
    }

    public void setGanhos(double ganhos) {
        this.ganhos = ganhos;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    public void setPorcentagem(float porcentagem) {
        this.porcentagem = porcentagem;
    }

    public double getPorcentagem() {
        double margem = ((ganhos-gastos)/ganhos);
        return DecimalFormatUtil.format(margem);
    }
}

