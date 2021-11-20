package com.ufpr.domain.itemDoPedido;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ufpr.domain.produto.Produto;

@Entity
@Table(name = "ItemDoPedido")
public class ItemDoPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idItemDoPedido")
	private Long idItemDoPedido;
	
	private int quantidade;
	
	@Column(name = "Pedido_Cliente_idCliente")
	private String idCliente;

	@ManyToOne
	@JoinColumn(name="Produto_idProduto", nullable=false)
	private Produto produto;
	
	public ItemDoPedido() {
		
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

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
