package com.somei.apisomei.repository;

import com.somei.apisomei.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

    Optional<List<Orcamento>> findBySolicitanteId(Long solicitanteId);
}
