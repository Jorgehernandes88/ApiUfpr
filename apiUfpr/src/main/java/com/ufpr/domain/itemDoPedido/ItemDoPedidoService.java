package com.ufpr.domain.itemDoPedido;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

@Service
public class ItemDoPedidoService {
	
	@Autowired
	private ItemDoPedidoRepository rep;
		
	public Iterable<ItemDoPedido> getItemDoPedido(){
		return rep.findAll();
	}
	
	public Optional<ItemDoPedido> getItemDoPedidoById(Long id){
		return rep.findById(id);
	}
	

	public ItemDoPedido save(ItemDoPedido ItemDoPedido) {
		return rep.save(ItemDoPedido);
	}
	
	
}
