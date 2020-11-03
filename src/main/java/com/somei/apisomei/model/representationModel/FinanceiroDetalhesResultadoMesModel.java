package com.somei.apisomei.model.representationModel;

import com.somei.apisomei.model.DepositoBancario;
import com.somei.apisomei.model.Profissional;
import com.somei.apisomei.model.Servico;
import com.somei.apisomei.model.enums.StatusServico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinanceiroDetalhesResultadoMesModel {

    private List<FinanceiroResultadoDiaModel> ganhos = new ArrayList<>();
    private List<FinanceiroResultadoDiaModel> previsao = new ArrayList<>();

    public FinanceiroDetalhesResultadoMesModel() {
    }

    public FinanceiroDetalhesResultadoMesModel(List<Servico> servicos, Profissional profissional, LocalDateTime finalMes) {
        //Filtrar apenas serviços concluídos
        servicos = servicos.stream().filter(s ->   s.getStatus() == StatusServico.FINALIZADO).collect(Collectors.toList());
        
        //CRIAR GANHOS
        int diaAtual = LocalDateTime.now().getDayOfMonth();
        for (int dia = 1; dia < diaAtual+1; dia++){
            //Filtra apenas os depositos deste dia
            int finalDia = dia;

            //Filtra apenas serviços deste dia ou anteriores
            List<Servico> servicosDia = servicos.stream().filter(s -> s.getDtConcluido().getDayOfMonth() <= finalDia).collect(Collectors.toList());

            float valorTotalDia = 0;
            for (Servico sd : servicosDia) {
                valorTotalDia += sd.getValorContratado();
            }

            FinanceiroResultadoDiaModel diaModel = new FinanceiroResultadoDiaModel();
            diaModel.setDia(dia);
            diaModel.setValor(valorTotalDia);
            this.ganhos.add(diaModel);
        }

        //CRIAR PREVISÃO
        float ganhoTotal = 0;
        for (Servico s : servicos) {
            ganhoTotal += s.getValorContratado();
        }

        if(ganhoTotal >0){
            float mediaGanhoDia = ganhoTotal / diaAtual;
            int diasRestantes = finalMes.getDayOfMonth() - diaAtual;

            for(int i = 1; i < diasRestantes+1; i++){
                FinanceiroResultadoDiaModel diaModel = new FinanceiroResultadoDiaModel();
                diaModel.setDia(i+diaAtual);
                diaModel.setValor(ganhoTotal + (mediaGanhoDia * i));
                this.previsao.add(diaModel);
            }
        }

    }

    public List<FinanceiroResultadoDiaModel> getGanhos() {
        return ganhos;
    }

    public void setGanhos(List<FinanceiroResultadoDiaModel> ganhos) {
        this.ganhos = ganhos;
    }

    public List<FinanceiroResultadoDiaModel> getPrevisao() {
        return previsao;
    }

    public void setPrevisao(List<FinanceiroResultadoDiaModel> previsao) {
        this.previsao = previsao;
    }
}
