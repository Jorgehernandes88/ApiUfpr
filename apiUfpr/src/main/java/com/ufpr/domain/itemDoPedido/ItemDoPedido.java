package com.ufpr.domain.itemDoPedido;

import java.security.PublicKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ItemDoPedido")
public class ItemDoPedido {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idItemDoPedido")
	private Long idItemDoPedido;
	
	private int quantidade;
	
	@Column(name = "Pedido_idPedido")
	private String idPedido;
	
	@Column(name = "Pedido_Cliente_idCliente")
	private String idCliente;
	
	@Column(name = "Produto_idProduto")
	private String idProduto;
	
	
	public ItemDoPedido() {
		
	}

	public ItemDoPedido(Long idItemDoPedido, int quantidade, String idPedido, String idCliente, String idProduto) {
		super();
		this.idItemDoPedido = idItemDoPedido;
		this.quantidade = quantidade;
		this.idPedido = idPedido;
		this.idCliente = idCliente;
		this.idProduto = idProduto;
	}

	public Long getIdItemDoPedido() {
		return idItemDoPedido;
	}

	public void setIdItemDoPedido(Long idItemDoPedido) {
		this.idItemDoPedido = idItemDoPedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}
	
}
