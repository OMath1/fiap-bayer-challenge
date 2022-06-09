package br.com.fiap.bayer.repository;

import br.com.fiap.bayer.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}