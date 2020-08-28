package com.somei.apisomei.repository;

import com.somei.apisomei.model.Pessoa;
import com.somei.apisomei.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByEmail(String email);
}
