package com.ufpr.domain.pedido;
import com.ufpr.domain.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository rep;

	@Autowired
	private ClienteRepository repCli;
		
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
	
	public Optional<PedidoDTO> getPedidoById(Long id){

		Optional<PedidoDTO> pedido = Optional.of(new PedidoDTO(rep.findById(id)));
		return pedido;
	}
	
	public List<Pedido> getPedidoByidCliente(String idCliente){

		List<Pedido> pedido = rep.findByidCliente(idCliente);
		
		return pedido;
	}

	public Pedido save(Pedido pedido) {
		return rep.save(pedido);
	}
}
