package com.ufpr.domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
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
	
	public List<Cliente> getClientesByCpf(String cpf){
		return rep.findByCpf(cpf);
	}

	public Cliente save(Cliente cliente) {

		//Buscar o cliente no banco de dados
		List<Cliente> clientes = getClientesByCpf(cliente.getCpf());
		if(clientes.isEmpty())
		{
			return rep.save(cliente);
		}
		else {
			return null;
		}
		
	}
	
	public Cliente update(Cliente cliente, Long id) {
		
		Assert.notNull(id,"Não foi possivel atualizar o registro");
		//Buscar o cliente no banco de dados
		Optional<Cliente> optional = getClienteById(id);
		if(optional.isPresent())
		{
			Cliente bd = optional.get();
			// Copiar as propriedades
			bd.setNome(cliente.getNome());
			bd.setSobreNome(cliente.getSobreNome());
			System.out.println("Cliente ID: " + bd.getId());
			
			//Atualizar o registro
			rep.save(bd);
			
			return bd;
		}
		else {
			throw new RuntimeException("Não foi possivel atualizar o registro");
		}
	}
	
	public void delete(Long id)
	{
		//Buscar o cliente no banco de dados
		Optional<Cliente> cliente = getClienteById(id);
		if(cliente.isPresent())
		{
			rep.deleteById(id);
		}
		
	}
	
}
