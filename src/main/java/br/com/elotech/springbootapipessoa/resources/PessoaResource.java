package br.com.elotech.springbootapipessoa.resources;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.elotech.springbootapipessoa.entities.Contato;
import br.com.elotech.springbootapipessoa.entities.Pessoa;
import br.com.elotech.springbootapipessoa.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public ResponseEntity<Page<Pessoa>> findAll() {
		return ResponseEntity.ok(pessoaService.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> getById(@PathVariable Long id) {
		return ResponseEntity.ok(pessoaService.getById(id));
	}

	@PostMapping
	public ResponseEntity<Pessoa> post(@RequestBody Pessoa pessoa) {
		Pessoa pessoaGerada = pessoaService.insert(pessoa);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoaGerada.getId())
				.toUri();

		return ResponseEntity.created(uri).body(pessoaGerada);
	}
	
	@PostMapping(value = "{idPessoa}/contato", produces = "application/json")
	public ResponseEntity<Set<Contato>> postContato(@PathVariable Long idPessoa, @RequestBody Contato contato) {
		Set<Contato> contatosAlterados = pessoaService.insertContatoPessoa(idPessoa, contato);
		return ResponseEntity.ok().body(contatosAlterados);	
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Pessoa> put(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		Pessoa pessoaAlterada = pessoaService.update(id, pessoa);
		return ResponseEntity.ok().body(pessoaAlterada);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pessoaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}