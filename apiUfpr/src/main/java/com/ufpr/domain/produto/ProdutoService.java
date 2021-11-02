package com.ufpr.domain.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository rep;

    public void setRepository(ProdutoRepository repository){
        this.rep = repository;
    }

    public Iterable<Produto> getProdutos(){
        return rep.findAll();
    }

    public Optional<Produto> getProdutoById(Long id){
        return rep.findById(id);
    }

    public Produto save(Produto produto) {
        return rep.save(produto);
    }

    public Produto update(Produto prod, Long id) {

        Assert.notNull(id,"Não foi possivel atualizar o registro");
        //Buscar o produto no banco de dados
        Optional<Produto> optional = getProdutoById(id);
        if(optional.isPresent())
        {
            Produto bd = optional.get();

            // Copiar as propriedades
            bd.setDescricao(prod.getDescricao());
            System.out.println("Produto ID: " + bd.getId());

            //Atualizar o registro
            rep.save(bd);

            return bd;
        }
        else {
            throw new RuntimeException("Não foi possivel atualizar o registro");
        }
    }

    public void delete(Long id) throws Exception
    {
        try {
            //Buscar o produto no banco de dados
            Optional<Produto> produto = getProdutoById(id);
            if (produto.isPresent()) {
                rep.deleteById(id);
            }
        }
    catch(Exception ex)
    {
        throw  ex;
    }
    }
}
