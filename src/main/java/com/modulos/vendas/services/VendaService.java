package com.modulos.vendas.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import com.modulos.comum.exception.ValidacaoException;
import com.modulos.vendas.dto.venda.VendaRequest;
import com.modulos.vendas.dto.venda.VendaResponse;
import com.modulos.vendas.entities.Cliente;
import com.modulos.vendas.entities.Produto;
import com.modulos.vendas.entities.Vendedor;
import com.modulos.vendas.repositories.ClienteRepository;
import com.modulos.vendas.repositories.ProdutoRepository;
import com.modulos.vendas.repositories.VendedorRepository;
import com.modulos.vendas.dto.VendaDto;
import com.modulos.vendas.entities.Venda;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.modulos.vendas.mappers.VendaMapper;
import com.modulos.vendas.repositories.VendaRepository;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;
    private final ProdutoRepository produtoRepository;
    private VendaMapper mapper = Mappers.getMapper(VendaMapper.class);

    public List<VendaResponse> findAll() {
        var vendas = vendaRepository.findAll();

        return vendas.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<Venda> buscarTodosPorIdVendedorEPeriodo(Integer idVendedor, LocalDateTime inicio, LocalDateTime fim) {
        return vendaRepository.findByVendedorIdAndDataBetween(idVendedor, inicio, fim);
    }

    public VendaResponse findById(Integer id) {
        var venda = vendaRepository.findById(id).orElseThrow(() -> new ValidacaoException("Venda Não Encontrada!!"));
        return mapper.toResponse(venda);
    }

    public Long contarDias(LocalDate inicio, LocalDate fim) {
        return ChronoUnit.DAYS.between(inicio, fim);
    }

    public VendaResponse save(VendaRequest request) {
        var venda = mapper.toDomain(request);
        venda = vendaRepository.save(validarCampos(venda, request));
        return mapper.toResponse(venda);
    }

    public VendaDto update(Integer id, VendaDto vendaDto) {
        vendaDto.setId(id);
        var venda = mapper.toDomain(vendaDto);
        venda = vendaRepository.save(venda);
        return mapper.toDto(venda);
    }

    public void delete(Integer id) {
        vendaRepository.deleteById(id);
    }

    private Venda validarCampos(Venda venda, VendaRequest request) {
        venda.setCliente(validarCliente(request));
        venda.setVendedor(validarVendedor(request));
        venda.setProdutos(validarProduto(request));

        return venda;
    }

    private Cliente validarCliente(VendaRequest request) {
        var clienteId = request.getCliente().getId();
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ValidacaoException("Cliente não Encontrado!!"));
    }

    private Vendedor validarVendedor(VendaRequest request) {
        return vendedorRepository.findById(request.getVendedor().getId())
                .orElseThrow(() -> new ValidacaoException("Vendedor não Encontrado!!"));
    }

    private List<Produto> validarProduto(VendaRequest request) {
        var existingProductIds = fetchExistingProductIds(request);

        return request.getProdutos().stream()
                .filter(produto -> existingProductIds.contains(produto.getId()))
                .collect(Collectors.toList());
    }

    public List<Integer> fetchExistingProductIds(VendaRequest request) {
        List<Produto> produtos = request.getProdutos();
        List<Integer> produtosNaoEncontrados = filterNonExistingProducts(produtos);
        handleNonExistingProducts(produtosNaoEncontrados);
        return mapProductIds(produtos);
    }

    private List<Integer> filterNonExistingProducts(List<Produto> produtos) {
        return produtos.stream()
                .map(Produto::getId)
                .filter(productId -> !produtoRepository.findById(productId).isPresent())
                .collect(Collectors.toList());
    }

    private void handleNonExistingProducts(List<Integer> produtosNaoEncontrados) {
        if (!produtosNaoEncontrados.isEmpty()) {
            throw new ValidacaoException("Os seguintes produtos não foram encontrados: " + produtosNaoEncontrados);
        }
    }

    private List<Integer> mapProductIds(List<Produto> produtos) {
        return produtos.stream()
                .map(Produto::getId)
                .collect(Collectors.toList());
    }
}
