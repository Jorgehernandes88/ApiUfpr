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
    private PedidoRepository repositorio;

    @Autowired
    private ClienteRepository repositorioCliente;

    public void setRepository(PedidoRepository repository) {
        this.repositorio = repository;
    }

    public List<PedidoDTO> getPedido() {

        List<Pedido> pedidos = repositorio.findAll();

        List<PedidoDTO> pedidosDTO = new ArrayList<>();

        for (Pedido p : pedidos) {
            PedidoDTO pedido = new PedidoDTO(p);
            pedido.setCliente(repositorioCliente.findById(Long.parseLong(p.getIdCliente())).get());
            pedidosDTO.add(pedido);
        }

        return pedidosDTO;
    }

    public PedidoDTO getPedidoPorId(Long id) {

        PedidoDTO pedido = new PedidoDTO();

        Optional<Pedido> itemPedido = repositorio.findById(id);
        if (itemPedido.isPresent()) {
            pedido = new PedidoDTO(itemPedido.get());
            pedido.setCliente(repositorioCliente.findById(Long.parseLong(itemPedido.get().getIdCliente())).get());
        }

        return pedido;

    }

    public List<PedidoDTO> getPedidoPorIdCliente(String idCliente) {

        List<Pedido> pedidos = repositorio.findByidCliente(idCliente);

        List<PedidoDTO> pedidosDTO = new ArrayList<>();

        for (Pedido p : pedidos) {
            PedidoDTO novoPedido = new PedidoDTO(p);
            novoPedido.setCliente(repositorioCliente.findById(Long.parseLong(p.getIdCliente())).get());
            pedidosDTO.add(novoPedido);
        }

        return pedidosDTO;
    }

    public Pedido save(Pedido pedido) {
        return repositorio.save(pedido);
    }
}
