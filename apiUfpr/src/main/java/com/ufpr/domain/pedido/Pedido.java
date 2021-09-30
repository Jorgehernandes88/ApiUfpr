package com.ufpr.domain.pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.crypto.Data;

import com.ufpr.domain.Cliente;
import com.ufpr.domain.itemDoPedido.ItemDoPedido;

import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

@Entity
@Table(name = "Pedido")
public class Pedido {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPedido")
	private Long idPedido;
	
	private String data;

	@ManyToOne
	@JoinColumn(name="Cliente_idCliente", nullable=false)
	private Cliente cliente;


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
