package com.ufpr.domain.pedido;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import com.ufpr.domain.itemDoPedido.ItemDoPedido;

@Entity
@Table(name = "Pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPedido")
	private Long idPedido;

	private String data;

	@Column(name = "Cliente_idCliente")
	private String idCliente;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="Pedido_idPedido")
	private List<ItemDoPedido> itensDoPedido = new ArrayList<>();

	public Pedido() {

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


	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public List<ItemDoPedido> getItensDoPedido() {
		return itensDoPedido;
	}

	public void setItensDoPedido(List<ItemDoPedido> itensDoPedido) {
		this.itensDoPedido = itensDoPedido;
	}

}