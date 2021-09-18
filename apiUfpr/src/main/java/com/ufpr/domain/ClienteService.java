package com.ufpr.domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository rep;
		
	public Iterable<Cliente> getClientes(){
		return rep.findAll();
	}
	
	public Optional<Cliente> getClienteById(Long id){
		return rep.findById(id);
	}
	
	public Iterable<Cliente> getClientesByCpf(String cpf){
		return rep.findByCpf(cpf);
	}

	public Cliente save(Cliente cliente) {
		return rep.save(cliente);
	}
	
	public List<Cliente> getClientesFake(){
		List<Cliente> clientes = new ArrayList<>();
	
		clientes.add(new Cliente(1L,"071.218.839-88","Jorge Miguel", "Hernandes"));
		clientes.add(new Cliente(1L,"071.589.698-98","Paloma", "Bittar"));
		clientes.add(new Cliente(1L,"071.587.325-77","Jeanine", "Hernandes"));

		return clientes;
	}
	
}
