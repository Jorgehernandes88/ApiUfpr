package com.ufpr.domain.pedido;
import com.ufpr.domain.ClienteRepository;
import com.ufpr.domain.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository rep;

	@Autowired
	private ClienteRepository repCli;

	public void setRepository(PedidoRepository repository){
		this.rep = repository;
	}
		
	public List<PedidoDTO> getPedido(){

		List<Pedido> pedidos = rep.findAll();
		List<PedidoDTO> pedidosDTO = new ArrayList<>();

		for (Pedido p : pedidos){
			PedidoDTO newPedido = new PedidoDTO(p);
			newPedido.setCliente(repCli.findById( Long.parseLong(p.getIdCliente())).get());
			pedidosDTO.add(newPedido);
		}

		return pedidosDTO;
	}
	
	public PedidoDTO getPedidoById(Long id){

		PedidoDTO pedido = new PedidoDTO();
		Optional<Pedido> itemPedido = rep.findById(id);

		if(itemPedido.isPresent()){
			pedido = new PedidoDTO(itemPedido.get());
			pedido.setCliente(repCli.findById( Long.parseLong(itemPedido.get().getIdCliente())).get());
		}

		return pedido;

	}
	
	public List<PedidoDTO> getPedidoByidCliente(String idCliente){

		List<Pedido> pedidos = rep.findByidCliente(idCliente);
		List<PedidoDTO> pedidosDTO = new ArrayList<>();
		
		for (Pedido p : pedidos){
			PedidoDTO newPedido = new PedidoDTO(p);
			newPedido.setCliente(repCli.findById( Long.parseLong(p.getIdCliente())).get());
			pedidosDTO.add(newPedido);
		}

		return pedidosDTO;
	}
	
	public Pedido save(Pedido pedido) throws Exception {
		try {
			return rep.save(pedido);
		}catch (Exception ex){
			throw new Exception();
		}
	}
}
