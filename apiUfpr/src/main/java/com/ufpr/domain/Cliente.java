package com.ufpr.domain;

import java.security.PublicKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCliente")
	private Long idCliente;
	
	private String cpf;
	private String nome;
	
	@Column(name = "sobreNome")
	private String sobreNome;
	
	public Cliente() {
		
	}
	
	public Cliente(Long idCliente, String cpf, String nome, String sobreNome) {
		this.idCliente = idCliente;
		this.cpf = cpf;
		this.nome = nome;
		this.sobreNome = sobreNome;
	}

	public Long getId() {
		return idCliente;
	}

	public void setId(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}
	
}
