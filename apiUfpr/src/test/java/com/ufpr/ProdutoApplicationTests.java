package com.ufpr;

import com.ufpr.api.ProdutosController;
import com.ufpr.domain.produto.Produto;
import com.ufpr.domain.produto.ProdutoRepository;
import com.ufpr.domain.produto.ProdutoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Objects;

@SpringBootTest
class ProdutoApplicationTests {

    @Autowired
    public ProdutosController controller;

    @Autowired
    public ProdutoService service;

    public ProdutoRepository repository;

    @Before
    public void setup() {
        controller = new ProdutosController();
        service = new ProdutoService();
        repository = Mockito.mock(ProdutoRepository.class);
        service.setRepository(repository);
    }

    @Test
    void deveInserirProduto() {
        Produto produto = new Produto();
        produto.setDescricao("Caneta");

        ResponseEntity<HashMap<String, String>> retorno = controller.post(produto);

        Assert.assertEquals(HttpStatus.OK, retorno.getStatusCode());
        Assert.assertEquals("{Status=Produto incluído com sucesso, idProduto=" + produto.getId().toString() + "}", Objects.requireNonNull(retorno.getBody()).toString());
    }

    @Test
    void deveRetornarErroDescricaoProduto() {
        Produto produto = new Produto();

        ResponseEntity<HashMap<String, String>> retorno = controller.post(produto);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Campo descrição não preenchido}", Objects.requireNonNull(retorno.getBody().toString()));
    }

    @Test
    void deveAtualizarProduto() {
        Produto produto = new Produto();
        produto.setDescricao("BorrachaUpdate");

        ResponseEntity<HashMap<String, String>> retorno = controller.put(1L, produto);

        Assert.assertEquals(HttpStatus.OK, retorno.getStatusCode());
        Assert.assertEquals("{Status=Produto atualizado com sucesso, idProduto=1}", Objects.requireNonNull(retorno.getBody()).toString());
    }

    @Test
    void deveRetornarErroAtualizarProduto() {
        Produto produto = new Produto();

        ResponseEntity<HashMap<String, String>> retorno = controller.put(1L, produto);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Campo descrição não preenchido}", Objects.requireNonNull(retorno.getBody().toString()));

    }

    @Test
    void deveRetornarErroExcluirProduto() {

        ResponseEntity<HashMap<String, String>> retorno = controller.delete(1l);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Este produto está vinculado a um pedido, exclua-o primeiro.}", Objects.requireNonNull(retorno.getBody().toString()));
    }

    @Test
    void deveExcluirProduto() {

        Produto produto = new Produto();
        produto.setDescricao("Caneta");

        controller.post(produto);

        ResponseEntity<HashMap<String, String>> retorno = controller.delete(produto.getId());

        Assert.assertEquals(HttpStatus.OK, retorno.getStatusCode());
        Assert.assertEquals("{Status=Produto excluido com sucesso, idProduto=" + produto.getId().toString() + "}", Objects.requireNonNull(retorno.getBody().toString()));
    }
}
