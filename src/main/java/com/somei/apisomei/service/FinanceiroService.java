package com.somei.apisomei.service;

import com.somei.apisomei.exception.NotFoundException;
import com.somei.apisomei.model.Lancamento;
import com.somei.apisomei.model.Profissional;
import com.somei.apisomei.repository.FinanceiroRepository;
import com.somei.apisomei.repository.LancamentoRepository;
import com.somei.apisomei.repository.ProfissionalRepository;
import com.somei.apisomei.util.CustomDate;
import com.somei.apisomei.util.CustomDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FinanceiroService {

    @Autowired
    LancamentoRepository lancamentoRepository;

    @Autowired
    ProfissionalRepository profissionalRepository;

    private final LocalDate inicioMes = this.getInicioMes();
    private final LocalDate finalMes = this.getFinalMes();

    private LocalDate getFinalMes() {
        LocalDate inicioMes = this.getInicioMes();
        LocalDate finalMes = inicioMes.plusMonths(1).plusDays(-1);

        //Teste
        CustomDate teste = CustomDate.byLocalDate(finalMes);
        System.out.println("Final mês:" + teste.toString());

        return finalMes;
    }

    private LocalDate getInicioMes() {
        LocalDate ref = LocalDate.now().plusDays(-2);
        LocalDate inicioMes = ref.plusDays((ref.getDayOfMonth()-1)*(-1));

        //Teste
        CustomDate teste = CustomDate.byLocalDate(inicioMes);
        System.out.println("Inicio mês: " + teste.toString());

        return inicioMes;
    }

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

        return readByMonth(profissional.getFinanceiro().getId());
    }

    public List<Lancamento> readMonthByProfissional(long idProfissional){
        Profissional profissional = profissionalRepository.findById(idProfissional)
                .orElseThrow(() -> new NotFoundException("Profissional não localizado"));

        return readByMonth(profissional.getFinanceiro().getId());
    }

    private List<Lancamento> readByMonth(long financeiroId){
        return lancamentoRepository
                        .findByFinanceiroIdAndDtVencimentoBetween(financeiroId, inicioMes, finalMes)
                .orElseThrow(() -> new NotFoundException("Não há lançamentos"));
    }
}
