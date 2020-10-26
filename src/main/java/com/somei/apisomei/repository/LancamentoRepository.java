package com.somei.apisomei.repository;

import com.somei.apisomei.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    Optional<List<Lancamento>> findByFinanceiroIdAndDtVencimentoBetween(long financeiroId, LocalDate start, LocalDate end);

}
