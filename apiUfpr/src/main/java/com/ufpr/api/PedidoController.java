package com.ufpr.api;

import java.util.Optional;

import com.ufpr.domain.ClienteService;
import com.ufpr.domain.pedido.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.domain.itemDoPedido.ItemDoPedidoService;
import com.ufpr.domain.pedido.Pedido;
import com.ufpr.domain.pedido.PedidoService;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;

	@Autowired
	private ClienteService serviceCli;

	@Autowired
	private ClienteService clienteService;
	private ItemDoPedidoService servicePedido;
	
	@GetMapping()
	public ResponseEntity<Iterable<PedidoDTO>> get() {
		return new ResponseEntity<>(service.getPedido(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDTO> get(@PathVariable("id") Long id) {
		
		Optional<PedidoDTO> pedido = service.getPedidoById(id);

		
		if(pedido.isPresent())
		{
			return ResponseEntity.ok().body(pedido.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public String post(@RequestBody Pedido pedido) {
	
		Pedido pedidos = service.save(pedido);
		
		return "[{idPedido: " + pedidos.getIdPedido() + "}]";
	}
	
}
