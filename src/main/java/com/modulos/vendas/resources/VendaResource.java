
package com.modulos.vendas.resources;

import com.modulos.vendas.dto.VendaDto;
import com.modulos.vendas.dto.venda.VendaRequest;
import com.modulos.vendas.dto.venda.VendaResponse;
import com.modulos.vendas.services.VendaService;
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

import java.util.List;

@RestController
@RequestMapping(value = "api/vendas")
public class VendaResource {
    @Autowired
    private VendaService service;

    @GetMapping
    public ResponseEntity<List<VendaResponse>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<VendaResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody VendaRequest request) {
        var venda = service.save(request);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(venda.getId()).toUri();
        HttpHeaders header = new HttpHeaders();
        header.add("id", venda.getId().toString());

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody VendaDto vendaDto) {
        service.update(id, vendaDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}