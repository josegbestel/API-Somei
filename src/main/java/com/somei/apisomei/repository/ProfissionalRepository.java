package com.somei.apisomei.repository;

import com.somei.apisomei.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

    Optional<Profissional> findByCnpj(String cnpj);
    Optional<Profissional> findByEmail(String email);
}
