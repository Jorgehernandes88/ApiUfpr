package com.ufpr.domain.pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.crypto.Data;

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
