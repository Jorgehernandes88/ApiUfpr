package com.ufpr.api;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ufpr.domain.ClienteService;
import com.ufpr.domain.validadores.ValidaCPF;
import com.ufpr.domain.Cliente;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClientesController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping()
	public ResponseEntity<Iterable<Cliente>> get() {
		return new ResponseEntity<>(service.getClientes(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> get( @PathVariable("id") Long id) {
		
		Optional<Cliente> cliente = service.getClienteById(id);
		
		if(cliente.isPresent())
		{
			return ResponseEntity.ok(cliente.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/cpf/{cpf}")
	public  ResponseEntity<List<Cliente>> get( @PathVariable("cpf") String cpf) {
		
		List<Cliente> cliente = service.getClientesByCpf(cpf);
		
		if(cliente.isEmpty())
		{
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(cliente);
		}
	}
	
	@PostMapping
	public ResponseEntity<HashMap<String, String>> post(@RequestBody Cliente cliente) {
		HashMap<String, String> map = new HashMap<>();

		if(cliente.getNome() == "" | cliente.getSobreNome() == "" | cliente.getCpf() == "")
		{
			map.put("Erro","Campos obrigatórios não preenchidos");
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
					
		}if (ValidaCPF.isCPF(ValidaCPF.RemovePontuacao(cliente.getCpf())) != true){
			map.put("Erro","CPF inválido");
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}else {
			Cliente PostCliente = service.save(cliente);
			if(PostCliente == null)
			{
				map.put("Erro","CPF já existente");
				return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
						
			}	
			else {
				map.put("idCliente",PostCliente.getId().toString());
				map.put("Status","Cliente incluido com sucesso");
				return new ResponseEntity<>(map,HttpStatus.OK);
						
			}
		}
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
