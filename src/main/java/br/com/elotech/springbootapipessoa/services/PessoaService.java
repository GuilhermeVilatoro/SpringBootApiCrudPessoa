package br.com.elotech.springbootapipessoa.services;

import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.elotech.springbootapipessoa.business.ValidacaoRegraCadastroContato;
import br.com.elotech.springbootapipessoa.business.ValidacaoRegraCadastroPessoa;
import br.com.elotech.springbootapipessoa.entities.Contato;
import br.com.elotech.springbootapipessoa.entities.Pessoa;
import br.com.elotech.springbootapipessoa.repositories.ContatoRepository;
import br.com.elotech.springbootapipessoa.repositories.PessoaRepository;
import br.com.elotech.springbootapipessoa.services.exceptions.DatabaseException;
import br.com.elotech.springbootapipessoa.services.exceptions.ResourceNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ContatoRepository contatoRepository;

	@Autowired
	private ValidacaoRegraCadastroPessoa validacaoRegraCadastroPessoa;

	@Autowired
	private ValidacaoRegraCadastroContato validacaoRegraCadastroContatos;

	public Page<Pessoa> findAll() {
		int page = 0;
		int size = 10;
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");

		return new PageImpl<>(pessoaRepository.findAll(), pageRequest, size);
	}

	public Pessoa getById(Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		return pessoa.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Pessoa insert(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	public Pessoa update(Long id, Pessoa pessoa) {
		try {
			validacaoRegraCadastroPessoa.validar(pessoa);

			Pessoa entidadePessoa = pessoaRepository.getOne(id);

			entidadePessoa.setNome(pessoa.getNome());
			entidadePessoa.setCpf(pessoa.getCpf());
			entidadePessoa.setDataNascimento(pessoa.getDataNascimento());

			return pessoaRepository.save(entidadePessoa);
		} catch (EntityNotFoundException ex) {
			throw new ResourceNotFoundException(id);
		}
	}

	public Set<Contato> insertContatoPessoa(Long idPessoa, Contato contato) {
		Pessoa pessoa = getById(idPessoa);

		validacaoRegraCadastroContatos.validar(contato);

		contato.setPessoa(pessoa);
		contatoRepository.save(contato);

		return pessoa.getContatos();
	}

	public void delete(Long id) {
		try {
			pessoaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DatabaseException(ex.getMessage());
		}
	}
}