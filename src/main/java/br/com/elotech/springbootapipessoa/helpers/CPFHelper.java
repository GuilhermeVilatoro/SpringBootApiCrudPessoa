package br.com.elotech.springbootapipessoa.helpers;

import java.io.Serializable;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class CPFHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	public static boolean isValid(String cpf) {
		try {
			new CPFValidator().assertValid(cpf);
			return true;
		} catch (InvalidStateException e) {
			return false;
		}
	}
}