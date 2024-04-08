package com.vendas.services;

import com.vendas.dto.produto.ProdutoRequest;
import com.vendas.dto.produto.ProdutoResponse;
import com.vendas.entities.Produto;
import com.vendas.mappers.ProdutoMapper;
import com.vendas.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;
    private ProdutoMapper ProdutoMapper = Mappers.getMapper(ProdutoMapper.class);

    public List<ProdutoResponse> findAll() {
        var produtos = repository.findAll();
        return ProdutoMapper.toListResponse(produtos);
    }

    public ProdutoResponse findById(Integer id) {
        var produto = getById(id);
        return ProdutoMapper.toResponse(produto);
    }

    public ProdutoResponse save(ProdutoRequest request) {
        var produto = ProdutoMapper.toDomain(request);
        repository.save(produto);
        return ProdutoMapper.toResponse(produto);
    }

    public ProdutoResponse update(Integer id, ProdutoRequest request) {
        var produto = getById(id);
        var produtoUpdate = ProdutoMapper.toDomain(request);
        produtoUpdate.setId(produto.getId());

        repository.save(produtoUpdate);
        return ProdutoMapper.toResponse(produtoUpdate);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private Produto getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ValidationException("Produto não encontrado"));
    }
}
