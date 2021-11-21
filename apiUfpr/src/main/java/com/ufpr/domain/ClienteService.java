package com.ufpr.domain;

import com.ufpr.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repositorio;

    public void setRepository(ClienteRepository repository) {
        this.repositorio = repository;
    }

    public Iterable<Cliente> getClientes() {
        return repositorio.findAll();
    }

    public Optional<Cliente> getClientePorId(Long id) {
        return repositorio.findById(id);
    }

    public List<Cliente> getClientesPorCpf(String cpf) {
        return repositorio.findByCpf(cpf);
    }

    public Cliente save(Cliente cliente) {

        List<Cliente> clientes = getClientesPorCpf(cliente.getCpf());

        if (clientes.isEmpty()) {
            return repositorio.save(cliente);
        } else {
            return null;
        }

    }

    public Cliente update(Cliente novosDadosCliente, Long id) {

        Assert.notNull(id, Strings.ERRO_ATUALIZAR_REGISTRO);

        Optional<Cliente> cliente = getClientePorId(id);

        if (cliente.isPresent()) {
            return atualizaCliente(novosDadosCliente, cliente);
        } else {
            throw new RuntimeException(Strings.ERRO_ATUALIZAR_REGISTRO);
        }
    }

    private Cliente atualizaCliente(Cliente novosDadosCliente, Optional<Cliente> cliente) {
        cliente.get().setNome(novosDadosCliente.getNome());
        cliente.get().setSobreNome(novosDadosCliente.getSobreNome());

        return repositorio.save(cliente.get());
    }

    public void delete(Long id) {
        repositorio.deleteById(id);
    }
}
