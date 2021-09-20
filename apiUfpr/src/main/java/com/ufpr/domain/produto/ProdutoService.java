package com.ufpr.domain.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository rep;

    public Iterable<Produto> getProdutos(){
        return rep.findAll();
    }

    public Optional<Produto> getProdutoById(Long id){
        return rep.findById(id);
    }
}
