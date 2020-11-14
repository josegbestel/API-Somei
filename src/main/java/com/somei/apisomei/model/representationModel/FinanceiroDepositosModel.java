package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.DepositoBancario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FinanceiroDepositosModel {

    private FinanceiroDepositoSaldoModel saldoConta;
    private List<FinanceiroDepositoModel> historico = new ArrayList<>();

    public FinanceiroDepositosModel() {
    }

    public FinanceiroDepositosModel(List<DepositoBancario> depositosMes, float saldoDisponivel, float saldoLiberar) {

        //Saldo na conta
        FinanceiroDepositoSaldoModel saldoModel = new FinanceiroDepositoSaldoModel();
        saldoModel.setSaldoDisponivel(saldoDisponivel);
        saldoModel.setSaldoALiberar(saldoLiberar);

        //Definir histórico\
        if(depositosMes != null){
            List<DepositoBancario> historicoDepositos = depositosMes.stream().filter(d -> d.getDtDeposito() != null).collect(Collectors.toList());
            historicoDepositos = historicoDepositos.stream().filter(d  -> d.getDtDeposito().isBefore(LocalDateTime.now())).collect(Collectors.toList());
            for (DepositoBancario hd : historicoDepositos) {
                this.historico.add(new FinanceiroDepositoModel(hd));
            }
        }
    }

    public FinanceiroDepositoSaldoModel getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(FinanceiroDepositoSaldoModel saldoConta) {
        this.saldoConta = saldoConta;
    }

    public List<FinanceiroDepositoModel> getHistorico() {
        return historico;
    }

    public void setHistorico(List<FinanceiroDepositoModel> historico) {
        this.historico = historico;
    }
}
