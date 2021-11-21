package com.ufpr.api;

import com.ufpr.domain.produto.Produto;
import com.ufpr.domain.produto.ProdutoService;
import com.ufpr.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutosController {

    @Autowired
    private ProdutoService service;
	
	@CrossOrigin
    @GetMapping()
    public ResponseEntity<Iterable<Produto>> get() {
        return new ResponseEntity<>(service.getProdutos(), HttpStatus.OK);
    }
	
	@CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Produto> get(@PathVariable("id") Long id) {

        Optional<Produto> produto = service.getProdutoPorId(id);

        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@CrossOrigin
    @PostMapping
    public ResponseEntity<HashMap<String, String>> post(@RequestBody Produto produto) {

        Produto prod = service.save(produto);

        HashMap<String, String> map = new HashMap<>();

        if (produtoValido(produto)) {
            map.put(Strings.ERRO, Strings.ERRO_INCLUIR_CAMPO_DESCRICAO);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

        } else {
            map.put("idProduto", prod.getId().toString());
            map.put(Strings.STATUS, Strings.SUCESSO_INCLUIR_PRODUTO);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    private boolean produtoValido(Produto prod) {
        return prod.getDescricao() == null || prod.getDescricao().isEmpty();
    }
	
	@CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> put(@PathVariable("id") Long id, @RequestBody Produto produto) {

        Produto prod = service.update(produto, id);

        HashMap<String, String> map = new HashMap<>();

        if (produtoValido(produto)) {
            map.put(Strings.ERRO, Strings.ERRO_INCLUIR_CAMPO_DESCRICAO);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

        } else {
            map.put("idProduto", prod.getId().toString());
            map.put(Strings.STATUS, Strings.SUCESSO_ATUALIZAR_PRODUTO);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }
	
	@CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> delete(@PathVariable("id") Long id) {

        HashMap<String, String> map = new HashMap<>();

        try {
            service.delete(id);
            map.put(Strings.STATUS, Strings.SUCESSO_EXCLUSAO_PRODUTO);
            map.put("idProduto", id.toString());
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            trataMsgErroDelete(e, map);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    private HashMap<String, String> trataMsgErroDelete(Exception e, HashMap<String, String> map) {
        if (e.getMessage().equals(Strings.ERRO_MENSAGEM_RESTRICAO)) {
            map.put(Strings.ERRO, Strings.ERRO_EXCLUSAO_VINC_PEDIDO);
        } else {
            map.put(Strings.ERRO, Strings.ERRO_EXCLUSAO_TENTE_NOVAMENTE);
        }
        return map;
    }
}

