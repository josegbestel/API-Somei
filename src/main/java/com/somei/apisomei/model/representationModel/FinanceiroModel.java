package com.somei.apisomei.model.representationModel;

public class FinanceiroModel {

    private FinanceiroResultadoMesModel resultadoMes;
    private FinanceiroMargemLucroModel margemLucro;
    private FinanceiroDepositosModel depositosBancarios;

    public FinanceiroModel() {
    }

    public FinanceiroResultadoMesModel getResultadoMes() {
        return resultadoMes;
    }

    public void setResultadoMes(FinanceiroResultadoMesModel resultadoMes) {
        this.resultadoMes = resultadoMes;
    }

    public FinanceiroMargemLucroModel getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(FinanceiroMargemLucroModel margemLucro) {
        this.margemLucro = margemLucro;
    }

    public FinanceiroDepositosModel getDepositosBancarios() {
        return depositosBancarios;
    }

    public void setDepositosBancarios(FinanceiroDepositosModel depositosBancarios) {
        this.depositosBancarios = depositosBancarios;
    }
}
