package com.aluracursos.paises.repository;

import com.aluracursos.paises.clase.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    Optional<Pais> findByNombre_ComunIgnoreCase(String comun);
    List<Pais> findTop5ByOrderByPoblacionDesc();
}
