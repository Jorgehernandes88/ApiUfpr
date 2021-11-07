package com.ufpr.domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
		
	public void setRepository(ClienteRepository repository) {
		this.clienteRepository = repository;
		
	}
	
	public Iterable<Cliente> getClientes(){
		return clienteRepository.findAll();
	}
	
	public Optional<Cliente> getClienteById(Long id){
		return clienteRepository.findById(id);
	}
	
	public List<Cliente> getClientesByCpf(String cpf){
		return clienteRepository.findByCpf(cpf);
	}

	public Cliente save(Cliente cliente) {

		List<Cliente> clientes = getClientesByCpf(cliente.getCpf());
		if(clientes.isEmpty())
		{
			return clienteRepository.save(cliente);
		}
		else {
			return null;
		}
		
	}
	
	public Cliente update(Cliente cliente, Long id) {
		
		Assert.notNull(id,"Não foi possivel atualizar o registro");
		Optional<Cliente> optional = getClienteById(id);
		
		if(optional.isPresent())
		{
			Cliente bd = optional.get();
			bd.setNome(cliente.getNome());
			bd.setSobreNome(cliente.getSobreNome());
			System.out.println("Cliente ID: " + bd.getId());
			
			clienteRepository.save(bd);
			return bd;
		}
		else {
			throw new RuntimeException("Não foi possivel atualizar o registro");
		}
	}
	
	public void delete(Long id)
	{
		Optional<Cliente> cliente = getClienteById(id);
		if(cliente.isPresent())
		{
			clienteRepository.deleteById(id);
		}
		
	}
}
