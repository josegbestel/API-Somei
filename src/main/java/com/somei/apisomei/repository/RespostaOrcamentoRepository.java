package com.somei.apisomei.repository;

import com.somei.apisomei.model.RespostaOrcamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RespostaOrcamentoRepository extends JpaRepository<RespostaOrcamento, Long> {

    Optional<List<RespostaOrcamento>> findByProfissionalIdOrderByDtRespostaDesc(long profissionalId);

}
