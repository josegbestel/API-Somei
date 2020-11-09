package com.somei.apisomei.repository;

import com.somei.apisomei.model.DepositoBancario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DepositoBancarioRepository extends JpaRepository<DepositoBancario, Long> {

    Optional<List<DepositoBancario>> findByFinanceiroIdAndDtDepositoGreaterThan(Long financeiroId, LocalDateTime dtDeposito);
}
