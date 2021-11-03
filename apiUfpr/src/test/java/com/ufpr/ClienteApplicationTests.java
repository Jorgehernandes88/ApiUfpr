package com.ufpr;

import java.util.HashMap;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufpr.api.ClientesController;
import com.ufpr.domain.Cliente;
import com.ufpr.domain.ClienteRepository;
import com.ufpr.domain.ClienteService;


@SpringBootTest
class ClienteApplicationTests {

	@Autowired
	private ClienteService clienteService;
	
    @Autowired
    public ClientesController controller;

    public ClienteRepository repository;

    public ExpectedException exception = ExpectedException.none();
	
    @Before
    public void setup() {
        controller = new ClientesController();
        clienteService = new ClienteService();
        repository = Mockito.mock(ClienteRepository.class);
        clienteService.setRepository(repository);
    }
    
 
	@Test
	void inserindoCliente() {
		Cliente cliente = new Cliente();
		cliente.setCpf("071.760.649-03");
		cliente.setNome("Testes");
		cliente.setSobreNome("Unitarios");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(cliente);

        Assert.assertEquals(HttpStatus.OK, retorno.getStatusCode());
        Assert.assertEquals("{Status=Cliente incluido com sucesso, idCliente=" + cliente.getId().toString() + "}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}

	@Test
	void IncluirClienteCpfExistente() {
		Cliente cliente = new Cliente();
		cliente.setCpf("071.760.649-03");
		cliente.setNome("Testes");
		cliente.setSobreNome("Unitarios");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(cliente);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=CPF já existente}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}
	
	@Test
	void IncluirClienteSemCPF() {
		Cliente cliente = new Cliente();
		cliente.setCpf("");
		cliente.setNome("Testes");
		cliente.setSobreNome("Unitarios");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(cliente);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Campos obrigatórios não preenchidos}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}

	@Test
	void IncluirClienteSemNome() {
		Cliente cliente = new Cliente();
		cliente.setCpf("071.218.839-88");
		cliente.setNome("");
		cliente.setSobreNome("Unitarios");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(cliente);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Campos obrigatórios não preenchidos}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}
	
	@Test
	void IncluirClienteSemSobrenome() {
		Cliente cliente = new Cliente();
		cliente.setCpf("071.218.839-88");
		cliente.setNome("Testes");
		cliente.setSobreNome("");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(cliente);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Campos obrigatórios não preenchidos}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}
	
	@Test
	void IncluirClienteEmBranco() {
		Cliente cliente = new Cliente();
		cliente.setCpf("");
		cliente.setNome("");
		cliente.setSobreNome("");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(cliente);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Campos obrigatórios não preenchidos}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}
	
	@Test
	void IncluirClienteVazio() {
		Cliente cliente = new Cliente();
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(cliente);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Campos obrigatórios não preenchidos}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}
	
	@Test
	void IncluirClienteCPFIncorreto() {
		Cliente cliente = new Cliente();
		cliente.setCpf("071.133.332.88");
		cliente.setNome("Testes");
		cliente.setSobreNome("teste");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(cliente);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=CPF inválido}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}

	@Test
	void AtualizandoCliente() {
		Cliente cliente = new Cliente();
		cliente.setCpf("071.760.649-03");
		cliente.setNome("Testes");
		cliente.setSobreNome("Unitarios - Update");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.put(15L, cliente);

        Assert.assertEquals(HttpStatus.OK, retorno.getStatusCode());
        Assert.assertEquals("{Status=Cliente atualizado com sucesso, idCliente=" + cliente.getId().toString() + "}", Objects.requireNonNull(retorno.getBody()).toString());
		
	}
	
	/*	
	@Test
	void excluindoCliente() {
		
		clienteService.delete(7L);
	}
	*/
	
	
}
