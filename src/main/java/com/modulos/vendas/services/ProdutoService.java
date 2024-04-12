package com.modulos.vendas.services;

import com.modulos.vendas.dto.produto.ProdutoRequest;
import com.modulos.vendas.dto.produto.ProdutoResponse;
import com.modulos.vendas.entities.Produto;
import com.modulos.vendas.mappers.ProdutoMapper;
import com.modulos.vendas.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;
    private ProdutoMapper mapper = Mappers.getMapper(ProdutoMapper.class);

    public List<ProdutoResponse> findAll() {
        var produtos = repository.findAll();
        return mapper.toListResponse(produtos);
    }

    public ProdutoResponse findById(Integer id) {
        var produto = getById(id);
        return mapper.toResponse(produto);
    }

    public ProdutoResponse save(ProdutoRequest request) {
        var produto = mapper.toDomain(request);
        repository.save(produto);
        return mapper.toResponse(produto);
    }

    public ProdutoResponse update(Integer id, ProdutoRequest request) {
        var produto = getById(id);
        var produtoUpdate = mapper.toDomain(request);
        produtoUpdate.setId(produto.getId());

        repository.save(produtoUpdate);
        return mapper.toResponse(produtoUpdate);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private Produto getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ValidationException("Produto não encontrado"));
    }
}
