package com.ufpr.domain.pedido;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository rep;
		
	public Iterable<Pedido> getPedido(){
		return rep.findAll();
	}
	
	public Optional<Pedido> getPedidoById(Long id){
		return rep.findById(id);
	}
	

	public Pedido save(Pedido pedido) {
		return rep.save(pedido);
	}
}
