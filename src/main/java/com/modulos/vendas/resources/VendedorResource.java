package com.modulos.vendas.resources;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import com.modulos.vendas.dto.vendedor.VendedorRequest;
import com.modulos.vendas.dto.vendedor.VendedorResponse;
import com.modulos.vendas.dto.vendedor.VendedorVendasResponse;
import com.modulos.vendas.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "api/vendedores")
public class VendedorResource {

    @Autowired
    private VendedorService service;

    @GetMapping
    public ResponseEntity<List<VendedorResponse>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedorResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<VendedorVendasResponse>> buscarPorPeriodo(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return ResponseEntity.ok().body(service.findByPeriodo(inicio, fim));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody VendedorRequest request) {
        var vendedorResponse = service.save(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(vendedorResponse.getId()).toUri();
        HttpHeaders header = new HttpHeaders();
        header.add("id", vendedorResponse.getId().toString());

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody VendedorRequest request) {
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
