package br.com.fiap.bayer.repository;

import br.com.fiap.bayer.model.Caso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasoRepository extends JpaRepository<Caso, Long> {
}