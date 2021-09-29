package com.ufpr.domain.itemDoPedido;

import java.security.PublicKey;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ufpr.domain.pedido.Pedido;

@Entity
@Table(name = "ItemDoPedido")
public class ItemDoPedido {
	
	//Chave primaria tabela ItemDoPedido
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idItemDoPedido")
	private Long idItemDoPedido;
	
	//Quantidade de itens
	private int quantidade;
	
	//ID do Cliente
	@Column(name = "Pedido_Cliente_idCliente")
	private String idCliente;

	//ID do Produto
	@Column(name = "Produto_idProduto")
	private String idProduto;
	
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

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

}
