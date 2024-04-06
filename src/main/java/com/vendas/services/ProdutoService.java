package com.vendas.services;

import com.vendas.dtos.produto.ProdutoRequest;
import com.vendas.dtos.produto.ProdutoResponse;
import com.vendas.entities.Produto;
import com.vendas.mappers.ProdutoMapper;
import com.vendas.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;
    private ProdutoMapper ProdutoMapper = Mappers.getMapper(ProdutoMapper.class);

    public List<ProdutoResponse> findAll() {
        return null;
    }

    public ProdutoResponse findById(Integer id) {
        return null;
    }

    public ProdutoResponse save(ProdutoRequest request) {
        return null;
    }

    public ProdutoResponse update(Integer id, ProdutoRequest request) {
        return null;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private Produto getById(Integer id) {
        return null;
    }

}
