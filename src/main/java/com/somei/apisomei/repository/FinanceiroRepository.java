package com.somei.apisomei.repository;

import com.somei.apisomei.model.Financeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceiroRepository extends JpaRepository<Financeiro, Long> {
}
