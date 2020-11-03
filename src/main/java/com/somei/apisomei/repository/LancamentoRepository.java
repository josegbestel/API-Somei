package com.somei.apisomei.repository;

import com.somei.apisomei.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    List<Lancamento> findByFinanceiroIdAndDtVencimentoBetween(long financeiroId, LocalDate start, LocalDate end);

}
