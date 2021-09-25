package com.ufpr.api;
import com.ufpr.domain.produto.Produto;
import com.ufpr.domain.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutosController {

    @Autowired
    private ProdutoService service;

    @GetMapping()
    public ResponseEntity<Iterable<Produto>> get() {
        return new ResponseEntity<>(service.getProdutos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> get(@PathVariable("id") Long id) {

        Optional<Produto> produto = service.getProdutoById(id);

        if(produto.isPresent()){
            return ResponseEntity.ok(produto.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
