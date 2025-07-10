package com.victornext.encurtador.repository;

import com.victornext.encurtador.model.Encurtador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncurtadorRepository extends JpaRepository<Long, Encurtador> {

}
