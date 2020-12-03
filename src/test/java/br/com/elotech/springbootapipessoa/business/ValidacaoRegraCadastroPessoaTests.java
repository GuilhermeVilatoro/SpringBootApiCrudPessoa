package br.com.elotech.springbootapipessoa.business;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.elotech.springbootapipessoa.business.exceptions.BusinessException;
import br.com.elotech.springbootapipessoa.entities.Pessoa;

class ValidacaoRegraCadastroPessoaTests {

	private ValidacaoRegraCadastroPessoa validacaoRegraCadastroPessoa;

	private Pessoa pessoa;

	@BeforeEach
	void setUp() throws Exception {
		pessoa = new Pessoa(1L, "VILATOROTESTE", "06158372048", Instant.now().minus(31L, ChronoUnit.DAYS));
		validacaoRegraCadastroPessoa = new ValidacaoRegraCadastroPessoa();
	}

	@Test
	void testAoRealizarOCadastroDePessoaSemInformarUmaPessoa() {
		assertThrows(BusinessException.class, () -> {
			validacaoRegraCadastroPessoa.validar(null);
		});
	}

	@Test
	void testAoRealizarOCadastroDePessoaSemInformarNomeValido() {
		pessoa.setNome(null);
		assertThrows(BusinessException.class, () -> {
			validacaoRegraCadastroPessoa.validar(pessoa);
		});
	}

	@Test
	void testAoRealizarOCadastroDePessoaSemInformarCPFValido() {
		pessoa.setCpf("sdasdas");
		assertThrows(BusinessException.class, () -> {
			validacaoRegraCadastroPessoa.validar(pessoa);
		});
	}

	@Test
	void testAoRealizarOCadastroDePessoaInformadoDataNascimentoFutura() {
		pessoa.setDataNascimento(Instant.now().plus(1L, ChronoUnit.DAYS));
		assertThrows(BusinessException.class, () -> {
			validacaoRegraCadastroPessoa.validar(pessoa);
		});
	}

	@Test
	void testAoRealizarOCadastroDePessoaValidaDeveSerRealizadoOCadastroSemLancarExcecao() {
		assertDoesNotThrow(() -> {
			validacaoRegraCadastroPessoa.validar(pessoa);
		});
	}

	@AfterEach
	void tearDown() throws Exception {
	}


}
