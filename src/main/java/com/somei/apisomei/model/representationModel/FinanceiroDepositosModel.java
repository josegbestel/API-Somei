package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.DepositoBancario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FinanceiroDepositosModel {

    private FinanceiroDepositoModel proximo;
    private List<FinanceiroDepositoModel> historico = new ArrayList<>();

    public FinanceiroDepositosModel() {
    }

    public FinanceiroDepositosModel(List<DepositoBancario> depositosMes) {

        //Definir proximo depósito
        DepositoBancario proximoDeposito = depositosMes.stream().filter(d -> d.getDtPrevista().isAfter(LocalDate.now())).collect(Collectors.toList()).get(0);
        this.proximo = new FinanceiroDepositoModel(proximoDeposito);

        //Definir histórico
        List<DepositoBancario> historicoDepositos = depositosMes.stream().filter(d -> d.getDtDeposito() != null).collect(Collectors.toList());
        historicoDepositos = historicoDepositos.stream().filter(d  -> d.getDtDeposito().isBefore(LocalDateTime.now())).collect(Collectors.toList());
        for (DepositoBancario hd : historicoDepositos) {
            this.historico.add(new FinanceiroDepositoModel(hd));
        }
    }

    public FinanceiroDepositoModel getProximo() {
        return proximo;
    }

    public void setProximo(FinanceiroDepositoModel proximo) {
        this.proximo = proximo;
    }

    public List<FinanceiroDepositoModel> getHistorico() {
        return historico;
    }

    public void setHistorico(List<FinanceiroDepositoModel> historico) {
        this.historico = historico;
    }
}
