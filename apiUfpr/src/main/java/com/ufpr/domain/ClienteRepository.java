package com.ufpr.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	List<Cliente> findByCpf( String cpf);	
}


