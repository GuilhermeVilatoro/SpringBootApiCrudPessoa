package br.com.elotech.springbootapipessoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.elotech.springbootapipessoa.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {	
}