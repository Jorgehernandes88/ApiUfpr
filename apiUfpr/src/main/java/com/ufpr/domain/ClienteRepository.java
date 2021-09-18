package com.ufpr.domain;

import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	Iterable<Cliente> findByCpf( String cpf);	
}
