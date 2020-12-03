package br.com.elotech.springbootapipessoa.business;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.elotech.springbootapipessoa.business.exceptions.BusinessException;
import br.com.elotech.springbootapipessoa.entities.Contato;

class ValidacaoRegraCadastroContatoTests {

	private ValidacaoRegraCadastroContato validacaoRegraCadastroContato;

	private Contato contato;

	@BeforeEach
	void setUp() throws Exception {
		contato = new Contato(1L, "CONTATOTESTE", "99999-9999", "teste@gmail.com", null);
		validacaoRegraCadastroContato = new ValidacaoRegraCadastroContato();
	}

	@Test
	void testAoRealizarOCadastroDeContatoSemInformarUmContato() {
		assertThrows(BusinessException.class, () -> {
			validacaoRegraCadastroContato.validar(null);
		});
	}

	@Test
	void testAoRealizarOCadastroDeContatoSemInformarNomeValido() {
		contato.setNome(null);
		assertThrows(BusinessException.class, () -> {
			validacaoRegraCadastroContato.validar(contato);
		});
	}

	@Test
	void testAoRealizarOCadastroDeContatoSemInformarTelefoneValido() {
		contato.setTelefone(null);
		assertThrows(BusinessException.class, () -> {
			validacaoRegraCadastroContato.validar(contato);
		});
	}

	@Test
	void testAoRealizarOCadastroDeContatoInformadoEmailInvalido() {
		contato.setEmail("te@@@.com.s@2@@@");
		assertThrows(BusinessException.class, () -> {
			validacaoRegraCadastroContato.validar(contato);
		});
	}

	@Test
	void testAoRealizarOCadastroDeContatoValidaDeveSerRealizadoOCadastroSemLancarExcecao() {
		assertDoesNotThrow(() -> {
			validacaoRegraCadastroContato.validar(contato);
		});
	}

	@AfterEach
	void tearDown() throws Exception {
	}
}