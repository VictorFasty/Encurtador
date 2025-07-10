package com.victornext.encurtador.repository;

import com.victornext.encurtador.model.Encurtador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EncurtadorRepository extends JpaRepository<Encurtador, Long> {
    Optional<Encurtador> findByShortCode(String shortCode);

}
