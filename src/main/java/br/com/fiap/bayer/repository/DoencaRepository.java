package br.com.fiap.bayer.repository;

import br.com.fiap.bayer.model.Doenca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoencaRepository extends JpaRepository<Doenca, Long> {
}