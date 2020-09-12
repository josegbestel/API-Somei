package com.somei.apisomei.repository;

import com.somei.apisomei.model.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SolicitanteRepository extends JpaRepository<Solicitante, Long> {

    Optional<Solicitante> findByCpf(String cpf);
    Optional<Solicitante> findByEmail(String email);
}
