package com.somei.apisomei.repository;

import com.somei.apisomei.model.CategoriaMei;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaMeiRepository extends JpaRepository<CategoriaMei, Long> {

    boolean existsByDescricao(String descricao);

    boolean existsByTitulo(String titulo);

    boolean existsByOcupacao(String ocupacao);

    Optional<List<CategoriaMei>> findByTituloContains(String titulo);

    List<CategoriaMei> findByTituloContainsIgnoreCase(String titulo);

    Optional<CategoriaMei> findByTitulo(String categoriaTitulo);
}
