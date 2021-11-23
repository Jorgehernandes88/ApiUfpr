package com.ufpr;

import com.ufpr.api.PedidoController;
import com.ufpr.api.ProdutosController;
import com.ufpr.domain.itemDoPedido.ItemDoPedido;
import com.ufpr.domain.pedido.Pedido;
import com.ufpr.domain.pedido.PedidoRepository;
import com.ufpr.domain.pedido.PedidoService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class PedidoApplicationTests {

    @Autowired
    public PedidoController controller;

    @Autowired
    public PedidoService service;

    public PedidoRepository repository;

    @Before
    public void setup() {
        controller = new PedidoController();
        service = new PedidoService();
        repository = Mockito.mock(PedidoRepository.class);
        service.setRepository(repository);
    }

    @Test
    void deveBuscarPedidos() {

        ResponseEntity<HashMap<String, String>> retorno = controller.get();

        Assert.assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void deveInserirPedido() {
        Produto produto = new Produto();
        produto.setId(5l);
        produto.setDescricao("Borracha");

        ItemDoPedido itemDoPedido = new ItemDoPedido();
        itemDoPedido.setIdItemDoPedido(100l);
        itemDoPedido.setProduto(produto);
        itemDoPedido.setQuantidade(10);
        itemDoPedido.setIdCliente("1");

        List<ItemDoPedido> listPedido = new ArrayList<ItemDoPedido>();
        listPedido.add(itemDoPedido);

        Pedido pedido = new Pedido();
        pedido.setIdPedido(100l);
        pedido.setData("");
        pedido.setIdCliente("1");
        pedido.setItensDoPedido(listPedido);

        ResponseEntity<HashMap<String, String>> retorno = controller.post(pedido);

        Assert.assertEquals(HttpStatus.OK, retorno.getStatusCode());
        Assert.assertEquals("{Status=Pedido incluído com sucesso}", Objects.requireNonNull(retorno.getBody()).toString());
    }

    @Test
    void deveRetornarErroInserirPedido() {
        Pedido pedido = new Pedido();

        ResponseEntity<HashMap<String, String>> retorno = controller.post(pedido);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Não foi possivel incluir esse pedido}", Objects.requireNonNull(retorno.getBody()).toString());
    }

    @Test
    void deveRetornarErroBuscarPedidoId() {

        ResponseEntity<HashMap<String, String>> retorno = controller.get(0l);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Não foi possivel encontrar esse pedido}", Objects.requireNonNull(retorno.getBody()).toString());
    }
}
