package com.ufpr.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.domain.ClienteService;
import com.ufpr.domain.Cliente;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClientesController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping()
	public  Iterable<Cliente> get() {
		return service.getClientes();
	}
	
	@GetMapping("/{id}")
	public  Optional<Cliente> get( @PathVariable("id") Long id) {
		return service.getClienteById(id);
	}

	@GetMapping("/cpf/{cpf}")
	public  Iterable<Cliente> get( @PathVariable("cpf") String cpf) {
		return service.getClientesByCpf(cpf);
	}
	
	@PostMapping
	public String post(@RequestBody Cliente cliente) {
	
		Cliente cli = service.save(cliente);
				
		return "[{idCliente: " + cli.getId() + "}]";
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
	
		Cliente cli = service.update(cliente, id);
				
		return "[{idCliente: " + cli.getId() + "}]";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
	
		service.delete(id);
				
		return "cliente deletado";
	}
	
	
}
