package com.somei.apisomei.repository;

import com.somei.apisomei.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    Optional<List<Avaliacao>> findFirst5ByDestinatarioId(long destinatarioId);
}
