package com.ufpr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ufpr.domain.Cliente;
import com.ufpr.domain.ClienteService;

@SpringBootTest
class ClienteApplicationTests {

	@Autowired
	private ClienteService clienteService;
	
	@Test
	void inserindoCliente() {
		Cliente cliente = new Cliente();
		cliente.setCpf("071.760.649-03");
		cliente.setNome("Testes");
		cliente.setSobreNome("Unitarios");
		
		clienteService.save(cliente);
	}

	@Test
	void atualizandoCliente() {
		Cliente cliente = new Cliente();
		cliente.setCpf("071.760.649-03");
		cliente.setNome("Testes");
		cliente.setSobreNome("Unitarios - Update");
		
		clienteService.update(cliente, 7L);
	}
	
	@Test
	void excluindoCliente() {
		
		clienteService.delete(7L);
	}
	
	
	
}
