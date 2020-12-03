package br.com.elotech.springbootapipessoa.business;

import org.springframework.stereotype.Component;

import br.com.elotech.springbootapipessoa.business.exceptions.BusinessException;
import br.com.elotech.springbootapipessoa.entities.Contato;
import br.com.elotech.springbootapipessoa.helpers.EmailHelper;

@Component
public class ValidacaoRegraCadastroContato {

	public void validar(Contato contato) {

		if (contato == null)
			throw new BusinessException("Deve ser informado o contato!");

		if (contato.getNome() == null || contato.getNome().equals(""))
			throw new BusinessException("Nome do contato inválido!");

		if (contato.getEmail() == null || contato.getEmail() == "" || !EmailHelper.isValid(contato.getEmail()))
			throw new BusinessException("Email do contato inválido!");

		if (contato.getTelefone() == null || contato.getTelefone().equals(""))
			throw new BusinessException("Telefone do contato inválido!");
	}
}