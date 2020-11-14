package com.somei.apisomei.service;

import com.somei.apisomei.exception.NotFoundException;
import com.somei.apisomei.model.*;
import com.somei.apisomei.model.enums.StatusDeposito;
import com.somei.apisomei.model.representationModel.FinanceiroDepositosModel;
import com.somei.apisomei.model.representationModel.FinanceiroMargemLucroModel;
import com.somei.apisomei.model.representationModel.FinanceiroModel;
import com.somei.apisomei.model.representationModel.FinanceiroResultadoMesModel;
import com.somei.apisomei.repository.DepositoBancarioRepository;
import com.somei.apisomei.repository.LancamentoRepository;
import com.somei.apisomei.repository.ProfissionalRepository;
import com.somei.apisomei.repository.ServicoRepository;
import com.somei.apisomei.service.juno.response.BalanceResponse;
import com.somei.apisomei.service.juno.response.TransferResponse;
import com.somei.apisomei.util.CustomDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class FinanceiroService {

    @Autowired
    LancamentoRepository lancamentoRepository;

    @Autowired
    ProfissionalRepository profissionalRepository;

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    DepositoBancarioRepository depositoBancarioRepository;

    public void editMetaMensal(Long profissionalId, Float meta){
        Profissional profissional = profissionalRepository.findById(profissionalId)
                .orElseThrow(() -> new NotFoundException("Profissional não localizado"));

        profissional.getFinanceiro().setMetaMensal(meta);
        profissionalRepository.save(profissional);
    }

    public List<Lancamento> createLancamento(Long idProfissional, Lancamento lancamento){
        Profissional profissional = profissionalRepository.findById(idProfissional)
                .orElseThrow(() -> new NotFoundException("Profissional não localizado"));

        profissional.getFinanceiro().addLancamento(lancamento);
        profissional = profissionalRepository.save(profissional);

        return readLancamentosByMonth(profissional.getFinanceiro().getId());
    }

    public List<Lancamento> readMonthByProfissional(long idProfissional){
        Profissional profissional = profissionalRepository.findById(idProfissional)
                .orElseThrow(() -> new NotFoundException("Profissional não localizado"));

        return readLancamentosByMonth(profissional.getFinanceiro().getId());
    }

    public FinanceiroModel abstractMonth(long idProfissional){
        Profissional profissional = profissionalRepository.findById(idProfissional)
                .orElseThrow(() -> new NotFoundException("Profissional não localizado"));

        FinanceiroModel financeiroModel = new FinanceiroModel();

        //Serviços realizados nos ultimos 30 dias
        Optional<List<Servico>> servicosOpt = servicoRepository
                .findByProfissionalIdAndDtConcluidoGreaterThan(idProfissional,
                        LocalDateTime.now().plusDays(-30));

        //MARGEM LUCRO
        if(servicosOpt.isPresent()){
            List<Servico> servicos = servicosOpt.get();

            FinanceiroMargemLucroModel margemLucroModel = new FinanceiroMargemLucroModel(servicos);

            if(margemLucroModel.getGanhos() > 0){
                //Define a margem de lucro no model do relatório
                financeiroModel.setMargemLucro(margemLucroModel);
            }
        }

        //RESULTADO MÊS
        //Obtem todos os serviços do profissional desde o início do mês
        Optional<List<Servico>> servicosMesOpt = servicoRepository
                .findByProfissionalIdAndDtConcluidoGreaterThan(idProfissional, this.getInicioMes());

        if(servicosMesOpt.isPresent()){
            List<Servico> servicosMes = servicosMesOpt.get();
            FinanceiroResultadoMesModel resultadoMesModel =
                    new FinanceiroResultadoMesModel(servicosMes, profissional, this.getFinalMes());

            //Define os resultados no model do relatório
            financeiroModel.setResultadoMes(resultadoMesModel);
        }

        //DEPÓSITOS BANCÁRIOS
        //Obtem o saldo na conta do profissional
        JunoService junoService = new JunoService();
        junoService.gerarTokenAcesso();
        BalanceResponse saldoConta = junoService.consultarSaldo(profissional);
        System.out.println("balance: " + saldoConta.getBalance()
                + "\nwithheldBalance: " + saldoConta.getWithheldBalance()
                + "\ntransferableBalance: " + saldoConta.getTransferableBalance());

        //Obtém todos os depósitos do profissional no mês
        Optional<List<DepositoBancario>> depositosMesOpt = depositoBancarioRepository
                .findByFinanceiroIdAndDtDepositoGreaterThan(profissional.getFinanceiro().getId(),
                        this.getInicioMes());

        if(depositosMesOpt.isPresent()){
            List<DepositoBancario> depositosMes = depositosMesOpt.get();
            FinanceiroDepositosModel depositosModel = new FinanceiroDepositosModel(depositosMes, saldoConta);

            //Define os depósitos no model do relatório
            financeiroModel.setDepositosBancarios(depositosModel);
        }else{
            FinanceiroDepositosModel depositosModel = new FinanceiroDepositosModel(null, saldoConta);

            //Define os depósitos no model do relatório
            financeiroModel.setDepositosBancarios(depositosModel);
        }

        return financeiroModel;
    }

    private List<Lancamento> readLancamentosByMonth(long financeiroId){
        return lancamentoRepository
                        .findByFinanceiroIdAndDtVencimentoBetween(financeiroId,
                                this.getInicioMes().toLocalDate(),
                                this.getFinalMes().toLocalDate());
    }

    public FinanceiroDepositosModel transferValue(Long idProfissional,Float valor){

        Profissional profissional = profissionalRepository.findById(idProfissional)
                .orElseThrow(() -> new NotFoundException("Profissional não localizado"));

        JunoService junoService = new JunoService();
        junoService.gerarTokenAcesso();

        TransferResponse transferResponse = junoService.efetuarTransferencia(profissional, valor);
        BalanceResponse balanceResponse = junoService.consultarSaldo(profissional);

        DepositoBancario deposito = new DepositoBancario();
        deposito.setValor(transferResponse.getAmount());
        deposito.setStatus(StatusDeposito.AGENDADO);
        deposito.setFinanceiro(profissional.getFinanceiro());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dtDpt = LocalDateTime.parse(transferResponse.getCreationDate(), formatter);
        deposito.setDtDeposito(dtDpt);
        LocalDateTime dtPrv = LocalDateTime.parse(transferResponse.getTransferDate(), formatter);
        deposito.setDtPrevista(dtPrv.toLocalDate());

        deposito = depositoBancarioRepository.save(deposito);
        Financeiro financeiro = profissional.getFinanceiro();
        financeiro.addDeposito(deposito);
        profissional.setFinanceiro(financeiro);
        profissionalRepository.save(profissional);

        Optional<List<DepositoBancario>> depositosMesOpt = depositoBancarioRepository
                .findByFinanceiroIdAndDtDepositoGreaterThan(profissional.getFinanceiro().getId(),
                        this.getInicioMes());

        if(depositosMesOpt.isPresent()){
            FinanceiroDepositosModel depositosModel = new FinanceiroDepositosModel(depositosMesOpt.get(), balanceResponse);
            return depositosModel;
        }else {
            FinanceiroDepositosModel depositosModel = new FinanceiroDepositosModel(null, balanceResponse);
            return depositosModel;
        }
    }

    private LocalDateTime getFinalMes() {
        LocalDateTime inicioMes = this.getInicioMes();
        LocalDateTime finalMes = inicioMes.plusMonths(1).plusDays(-1);

        finalMes = LocalDateTime.of(finalMes.getYear(), finalMes.getMonth(), finalMes.getDayOfMonth(), 23, 59);

        //LOG
        CustomDateTime teste = CustomDateTime.byLocalDateTime(finalMes);
        System.out.println("Final mês:" + teste.toString());

        return finalMes;
    }

    private LocalDateTime getInicioMes() {
        LocalDateTime ref = LocalDateTime.now().plusDays(-2);
        LocalDateTime inicioMes = ref.plusDays((ref.getDayOfMonth()-1)*(-1));

        inicioMes = LocalDateTime.of(inicioMes.getYear(), inicioMes.getMonth(), inicioMes.getDayOfMonth(), 0, 0);

        //LOG
        CustomDateTime teste = CustomDateTime.byLocalDateTime(inicioMes);
        System.out.println("Inicio mês: " + teste.toString());

        return inicioMes;
    }
}
