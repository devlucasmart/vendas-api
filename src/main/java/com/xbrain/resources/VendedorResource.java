package com.xbrain.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.xbrain.dtos.VendedorDto;
import com.xbrain.services.VendedorService;

@RestController
@RequestMapping(value = "/vendedor")
public class VendedorResource {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public ResponseEntity<List<VendedorDto>> buscarTodos() {
        return ResponseEntity.ok().body(vendedorService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedorDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(vendedorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody VendedorDto vendedorDto) {
        vendedorDto = vendedorService.inserir(vendedorDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(vendedorDto.getId()).toUri();
        HttpHeaders header = new HttpHeaders();
        header.add("id", vendedorDto.getId().toString());

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody VendedorDto vendedorDto) {
        vendedorService.atualizar(id, vendedorDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        vendedorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
