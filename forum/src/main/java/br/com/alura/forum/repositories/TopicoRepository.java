package br.com.alura.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.entities.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

}
