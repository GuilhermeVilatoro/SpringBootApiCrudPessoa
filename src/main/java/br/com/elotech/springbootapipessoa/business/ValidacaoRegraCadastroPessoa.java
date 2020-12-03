package br.com.elotech.springbootapipessoa.business;

import java.time.Instant;

import org.springframework.stereotype.Component;

import br.com.elotech.springbootapipessoa.business.exceptions.BusinessException;
import br.com.elotech.springbootapipessoa.entities.Pessoa;
import br.com.elotech.springbootapipessoa.helpers.CPFHelper;

@Component
public class ValidacaoRegraCadastroPessoa {

	public void validar(Pessoa pessoa) {

		if (pessoa == null)
			throw new BusinessException("Deve ser informada uma pessoa!");

		if (pessoa.getNome() == null || pessoa.getNome().equals(""))
			throw new BusinessException("Nome da pessoa inválido!");

		if (pessoa.getCpf() == null || !CPFHelper.isValid(pessoa.getCpf()))
			throw new BusinessException("CPF inválido!");

		if (pessoa.getDataNascimento() == null || pessoa.getDataNascimento().isAfter(Instant.now()))
			throw new BusinessException("Data de nascimento inválida!");
	}
}