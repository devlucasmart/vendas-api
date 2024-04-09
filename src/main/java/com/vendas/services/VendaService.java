package com.vendas.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.vendas.comum.exception.ValidacaoException;
import com.vendas.dto.venda.VendaRequest;
import com.vendas.dto.venda.VendaResponse;
import com.vendas.entities.Cliente;
import com.vendas.entities.Produto;
import com.vendas.entities.Vendedor;
import com.vendas.repositories.ClienteRepository;
import com.vendas.repositories.ProdutoRepository;
import com.vendas.repositories.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.vendas.dto.VendaDto;
import com.vendas.entities.Venda;
import com.vendas.mappers.VendaMapper;
import com.vendas.repositories.VendaRepository;

import javax.validation.ValidationException;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;
    private final ProdutoRepository produtoRepository;
    private VendaMapper VendaMapper = Mappers.getMapper(VendaMapper.class);

    public List<VendaDto> buscarTodos() {
        var vendas = vendaRepository.findAll();
        var vendaDtos = new ArrayList<VendaDto>();
        for (Venda venda : vendas) {
            vendaDtos.add(VendaMapper.toDto(venda));
        }
        return vendaDtos;
    }

    public List<Venda> buscarTodosPorIdVendedorEPeriodo(Integer idVendedor, LocalDateTime inicio, LocalDateTime fim) {
        return vendaRepository.findByVendedorIdAndDataBetween(idVendedor, inicio, fim);
    }

    public VendaDto buscarPorId(Integer id) {
        var venda = vendaRepository.findById(id).orElseThrow(() -> new ValidacaoException("Venda Não Encontrada!!"));
        return VendaMapper.toDto(venda);
    }

    public Long contarDias(LocalDate inicio, LocalDate fim) {
        return ChronoUnit.DAYS.between(inicio, fim);
    }

    public VendaResponse inserir(VendaRequest request) {
        var venda = VendaMapper.toDomain(request);
        venda = vendaRepository.save(validarCampos(venda, request));
        return VendaMapper.toResponse(venda);
    }

    public VendaDto atualizar(Integer id, VendaDto vendaDto) {
        vendaDto.setId(id);
        var venda = VendaMapper.toDomain(vendaDto);
        venda = vendaRepository.save(venda);
        return VendaMapper.toDto(venda);
    }

    public void deletar(Integer id) {
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
        return clienteRepository.findById(clienteId).orElseThrow(() -> new ValidacaoException("Cliente não Encontrado!!"));
    }

    private Vendedor validarVendedor(VendaRequest request) {
        return vendedorRepository.findById(request.getVendedor().getId()).orElseThrow(() -> new ValidacaoException("Vendedor não Encontrado!!"));
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
