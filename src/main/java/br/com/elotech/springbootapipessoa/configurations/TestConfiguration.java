package br.com.elotech.springbootapipessoa.configurations;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.elotech.springbootapipessoa.entities.Contato;
import br.com.elotech.springbootapipessoa.entities.Pessoa;
import br.com.elotech.springbootapipessoa.repositories.ContatoRepository;
import br.com.elotech.springbootapipessoa.repositories.PessoaRepository;

@Configuration
@Profile("test")
public class TestConfiguration implements CommandLineRunner {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ContatoRepository contatoRepository;

	@Override
	public void run(String... args) throws Exception {
		Pessoa guilherme = new Pessoa(null, "Guilherme", "11111111111", Instant.parse("1989-04-01T19:53:07Z"));
		Pessoa thais = new Pessoa(null, "Thais", "22222222222", Instant.parse("1996-04-01T19:53:07Z"));
		Pessoa vinicius = new Pessoa(null, "Vinicius", "33333333333", Instant.parse("2017-04-01T19:53:07Z"));

		List<Pessoa> pessoas = Arrays.asList(guilherme, thais, vinicius);
		pessoaRepository.saveAll(pessoas);

		Contato contato1 = new Contato(null, "Contato1", "99999-9999", "contato1@gmail.com", guilherme);
		Contato contato2 = new Contato(null, "Contato2", "88888-8888", "contato2@gmail.com", thais);
		Contato contato3 = new Contato(null, "Contato3", "77777-7777", "contato3@gmail.com", guilherme);

		List<Contato> contatos = Arrays.asList(contato1, contato2, contato3);
		contatoRepository.saveAll(contatos);
	}
}