package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.DepositoBancario;
import com.somei.apisomei.model.Profissional;
import com.somei.apisomei.model.Servico;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

public class FinanceiroResultadoMesModel {

    private float saldoAtual;
    private float metaMensal;
    private float valorPrevisao;
    private FinanceiroDetalhesResultadoMesModel detalhes;

    public FinanceiroResultadoMesModel() {
    }
    
    public FinanceiroResultadoMesModel(List<Servico> servicos, Profissional profissional, LocalDateTime finalMes){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        //Meta mensal
        this.metaMensal = profissional.getFinanceiro().getMetaMensal();

        //Saldo atual
        this.saldoAtual = 0;
        for (Servico s : servicos) {
            saldoAtual += s.getValorContratado();
        }
        this.saldoAtual = Float.parseFloat(df.format(this.saldoAtual).replace(",", "."));

        //Detalhes
        this.detalhes = new FinanceiroDetalhesResultadoMesModel(servicos, profissional, finalMes);

        //Previsão
        float totalDias = LocalDateTime.now().getDayOfMonth();
        float mediaGanhoDiaPrevisao = this.saldoAtual  / totalDias;
        float diasRestantes = finalMes.getDayOfMonth() - totalDias;
        this.valorPrevisao = mediaGanhoDiaPrevisao * diasRestantes;
        String number = df.format(this.valorPrevisao);
        number  = number.replaceAll("\\.", "");
        number  = number.replace(",", ".");

        this.valorPrevisao = Float.parseFloat(number);
    }

    public float getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(float saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public float getMetaMensal() {
        return metaMensal;
    }

    public void setMetaMensal(float metaMensal) {
        this.metaMensal = metaMensal;
    }

    public float getValorPrevisao() {
        return valorPrevisao;
    }

    public void setValorPrevisao(float valorPrevisao) {
        this.valorPrevisao = valorPrevisao;
    }

    public FinanceiroDetalhesResultadoMesModel getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(FinanceiroDetalhesResultadoMesModel detalhes) {
        this.detalhes = detalhes;
    }
}
