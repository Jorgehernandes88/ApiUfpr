package com.ufpr.api;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ufpr.domain.ClienteService;
import com.ufpr.domain.pedido.PedidoDTO;
import com.ufpr.domain.pedido.PedidoService;
import com.ufpr.domain.validadores.CPF;
import com.ufpr.utils.Strings;
import com.ufpr.domain.Cliente;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClientesController {
	
	@Autowired
	private ClienteService service;
	
	@Autowired
	private PedidoService pedidoservice;
	
	 @CrossOrigin
	@GetMapping()
	public ResponseEntity<Iterable<Cliente>> get() {
		return new ResponseEntity<>(service.getClientes(),HttpStatus.OK);
	}
	
	@CrossOrigin
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
	
	@CrossOrigin
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
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<HashMap<String, String>> post(@RequestBody Cliente cliente) {
		HashMap<String, String> map = new HashMap<>();

		if(ClienteValido(cliente))
		{
			map.put(Strings.ERRO,Strings.ERRO_INCLUIR_CAMPOS_OBRIGATORIOS);
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
					
		}if (CPF.valido(CPF.removePontuacao(cliente.getCpf())) != true){
			map.put(Strings.ERRO,Strings.ERRO_CPF_INVALIDO);
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}else {
			Cliente PostCliente = service.save(cliente);
			if(PostCliente == null)
			{
				map.put(Strings.ERRO,Strings.ERRO_CPF_EXISTENTE);
				return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);		
			}	
			else {
				map.put("idCliente",PostCliente.getId().toString());
				map.put(Strings.STATUS,Strings.SUCESSO_INCLUIR_CLIENTE);
				return new ResponseEntity<>(map,HttpStatus.OK);					
			}
		}
	}
	
	@CrossOrigin
	@PutMapping("/{id}")
	public ResponseEntity<HashMap<String, String>> put(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
			
        HashMap<String, String> map = new HashMap<>();
        
        if (ClienteValido(cliente))
        {
			map.put(Strings.ERRO,Strings.ERRO_INCLUIR_CAMPOS_OBRIGATORIOS);
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }else {
        	Cliente cli = service.update(cliente, id);
        	
			map.put("idCliente",cli.getId().toString());
			map.put(Strings.STATUS,Strings.SUCESSO_ATUALIZAR_CLIENTE);
			return new ResponseEntity<>(map,HttpStatus.OK);	
        }
	}
	
	@CrossOrigin
	@DeleteMapping("/{id}")
	public ResponseEntity<HashMap<String, String>> delete(@PathVariable("id") Long id) {
		HashMap<String, String> map = new HashMap<>();
		List<PedidoDTO> pedido = pedidoservice.getPedidoPorIdCliente(id.toString());
		
		if(pedido.isEmpty()) {
			service.delete(id);
			
			map.put(Strings.STATUS,Strings.SUCESSO_EXCLUSAO_CLIENTE);
			return new ResponseEntity<>(map,HttpStatus.OK);	
		}else {
			map.put(Strings.ERRO,Strings.ERRO_CLIENTE_TEM_PEDIDO);
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);	
		}
	}
	
    private boolean ClienteValido(Cliente cliente) {
        return cliente.getNome() == "" | cliente.getSobreNome() == "" | cliente.getCpf() == "" 
        		| cliente.getNome() == null | cliente.getSobreNome() == null | cliente.getCpf() == null;
    }
}
