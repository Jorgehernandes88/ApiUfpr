package com.ufpr.domain.pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	List<Pedido> findByidCliente(String idCliente);
		
}

