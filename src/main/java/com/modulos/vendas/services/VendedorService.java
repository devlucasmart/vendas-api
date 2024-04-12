package com.modulos.vendas.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.modulos.comum.exception.ValidacaoException;
import com.modulos.vendas.dto.vendedor.VendedorRequest;
import com.modulos.vendas.dto.vendedor.VendedorResponse;
import com.modulos.vendas.dto.vendedor.VendedorVendasResponse;
import com.modulos.vendas.entities.Venda;
import com.modulos.vendas.entities.Vendedor;
import com.modulos.vendas.mappers.VendaMapper;
import com.modulos.vendas.mappers.VendedorMapper;
import com.modulos.vendas.repositories.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendedorService {
    private final VendedorRepository repository;
    private final VendaService vendaService;
    private VendedorMapper vendedorMapper = Mappers.getMapper(VendedorMapper.class);
    private VendaMapper mapper = Mappers.getMapper(VendaMapper.class);

    public List<VendedorResponse> findAll() {
        var vendedores = repository.findAll();

        return vendedores.stream()
                .map(vendedor -> {
                    var vendasResponse = vendedor.getVendas()
                            .stream()
                            .map(mapper::toResponseIgnoreVendedor)
                            .collect(Collectors.toList());

                    var vendedorResponse = vendedorMapper.toResponseIgnoreVendas(vendedor);
                    vendedorResponse.setVendas(vendasResponse);
                    return vendedorResponse;
                })
                .collect(Collectors.toList());
    }

    public VendedorResponse findById(Integer id) {
        var vendedor = repository.findById(id).orElseThrow(() -> new ValidacaoException("Vendedor não Encontrado!!"));
        return vendedorMapper.toResponse(vendedor);
    }

    public List<VendedorVendasResponse> findByPeriodo(LocalDate inicio, LocalDate fim) {
        var inicioDateTime = LocalDateTime.of(inicio, LocalTime.of(0, 0, 0));
        var fimDateTime = LocalDateTime.of(fim, LocalTime.of(23, 59, 59));
        var vendedores = repository.findAll();

        return vendedores.stream()
                .map(vendedor -> criarVendedorVendasResponse(vendedor, inicioDateTime, fimDateTime))
                .collect(Collectors.toList());
    }

    public VendedorResponse save(VendedorRequest request) {
        var vendedor = vendedorMapper.toDomain(request);
        vendedor = repository.save(vendedor);
        return vendedorMapper.toResponse(vendedor);
    }

    public VendedorResponse update(Integer id, VendedorRequest request) {
        var vendedorExistente = repository.findById(id).orElseThrow(() -> new ValidacaoException("Vendedor não Encontrado"));
        var vendedorUpdate = vendedorMapper.toDomain(request);
        vendedorUpdate.setId(vendedorExistente.getId());

        return vendedorMapper.toResponse(repository.save(vendedorUpdate));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private VendedorVendasResponse criarVendedorVendasResponse(Vendedor vendedor, LocalDateTime inicio, LocalDateTime fim) {
        var vendas = vendaService.buscarTodosPorIdVendedorEPeriodo(vendedor.getId(), inicio, fim);

        return new VendedorVendasResponse(
                vendedor.getId(),
                vendedor.getNome(),
                vendas.size(),
                calcularMediaDiaria(vendas.size(), inicio.toLocalDate(), fim.toLocalDate()),
                calcularValorTotalVendas(vendas)
        );
    }

    private double calcularMediaDiaria(int totalVendas, LocalDate inicio, LocalDate fim) {
        return (double) totalVendas / vendaService.contarDias(inicio, fim);
    }

    private double calcularValorTotalVendas(List<Venda> vendas) {
        return vendas.stream()
                .mapToDouble(Venda::getValor)
                .sum();
    }
}