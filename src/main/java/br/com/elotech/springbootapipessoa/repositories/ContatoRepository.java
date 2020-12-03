package br.com.elotech.springbootapipessoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.elotech.springbootapipessoa.entities.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}