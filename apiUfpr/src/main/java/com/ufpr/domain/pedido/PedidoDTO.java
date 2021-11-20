package com.ufpr.domain.pedido;

import com.ufpr.domain.Cliente;
import com.ufpr.domain.ClienteService;
import com.ufpr.domain.itemDoPedido.ItemDoPedido;
import com.ufpr.domain.pedido.Pedido;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoDTO {

    private ClienteService serviceCliente;

    private Long idPedido;

    private String data;

    private Cliente cliente;

    private List<ItemDoPedido> itensDoPedido = new ArrayList<>();

    public PedidoDTO(Pedido p) {
        this.idPedido = p.getIdPedido();
        this.data = p.getData();
        this.itensDoPedido = p.getItensDoPedido();
    }

    public PedidoDTO() {
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data =  data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemDoPedido> getItensDoPedido() {
        return itensDoPedido;
    }

    public void setItensDoPedido(List<ItemDoPedido> itensDoPedido) {
        this.itensDoPedido = itensDoPedido;
    }

}