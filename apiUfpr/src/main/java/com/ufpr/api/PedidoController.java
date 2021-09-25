package com.ufpr.api;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
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

import com.ufpr.domain.itemDoPedido.ItemDoPedido;
import com.ufpr.domain.itemDoPedido.ItemDoPedidoService;
import com.ufpr.domain.pedido.Pedido;
import com.ufpr.domain.pedido.PedidoService;


@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	private ItemDoPedidoService servicePedido;
	
	@GetMapping()
	public ResponseEntity<Iterable<Pedido>> get() {
		return new ResponseEntity<>(service.getPedido(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> get( @PathVariable("id") Long id) {
		
		Optional<Pedido> pedido = service.getPedidoById(id);
		
		if(pedido.isPresent())
		{
			return ResponseEntity.ok(pedido.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public String post(@RequestBody Pedido pedido, ItemDoPedido itens) {
	
		Pedido pedidos = service.save(pedido);
		ItemDoPedido itensP = servicePedido.save(itens); 
		
				
		return "[{idCliente: " + pedidos.getIdPedido() + "}]";
	}
	
}
