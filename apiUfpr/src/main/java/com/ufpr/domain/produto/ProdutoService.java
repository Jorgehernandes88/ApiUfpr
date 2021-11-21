package com.ufpr.domain.produto;

import com.ufpr.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repositorio;

    public void setRepository(ProdutoRepository repository) {
        this.repositorio = repository;
    }

    public Iterable<Produto> getProdutos() {
        return repositorio.findAll();
    }

    public Optional<Produto> getProdutoPorId(Long id) {
        return repositorio.findById(id);
    }

    public Produto save(Produto produto) {
        return repositorio.save(produto);
    }

    public Produto update(Produto prod, Long id) {

        Assert.notNull(id, Strings.ERRO_ATUALIZAR_REGISTRO);

        Optional<Produto> optional = getProdutoPorId(id);

        if (optional.isPresent())
            return AtualizaProduto(optional, prod);
        else
            throw new RuntimeException(Strings.ERRO_ATUALIZAR_REGISTRO);

    }

    private Produto AtualizaProduto(Optional<Produto> optional, Produto prod) {

        Produto produto = optional.get();
        produto.setDescricao(prod.getDescricao());
        repositorio.save(produto);

        return produto;
    }

    public void delete(Long id) throws Exception {
        try {

            Optional<Produto> produto = getProdutoPorId(id);

            if (produto.isPresent())
                repositorio.deleteById(id);

        } catch (Exception ex) {
            throw ex;
        }
    }
}
