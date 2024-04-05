
package com.vendas.resources;

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

import com.vendas.dtos.VendaDto;
import com.vendas.services.VendaService;

@RestController
@RequestMapping(value = "/vendas")
public class VendaResource {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public ResponseEntity<List<VendaDto>> buscarTodos() {
        return ResponseEntity.ok().body(vendaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(vendaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody VendaDto vendaDto) {
        vendaDto = vendaService.inserir(vendaDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(vendaDto.getId()).toUri();
        HttpHeaders header = new HttpHeaders();
        header.add("id", vendaDto.getId().toString());

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody VendaDto vendaDto) {
        vendaService.atualizar(id, vendaDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        vendaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}