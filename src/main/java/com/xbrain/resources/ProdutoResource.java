package com.xbrain.resources;

import com.xbrain.entities.Produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {

    @GetMapping
    public ResponseEntity<Produto> findAll() {
        Produto produto = new Produto(1, "tenis", 30.0, 1);
        return ResponseEntity.ok().body(produto);

    }

}
