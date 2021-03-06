package com.somei.apisomei.repository;

import com.somei.apisomei.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    Optional<List<Servico>> findBySolicitanteId(Long solicitanteId);

    Optional<List<Servico>> findByProfissionalId(long profissionalId);

    //OrderByDtConcluidoDesc
    Optional<List<Servico>> findByProfissionalIdAndDtConcluidoGreaterThan(long profissionalId, LocalDateTime dtConcluido);
}
