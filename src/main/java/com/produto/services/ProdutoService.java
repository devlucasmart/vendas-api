package com.produto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produto.dtos.ProdutoDto;
import com.produto.entities.Produto;
import com.produto.mappers.ProdutoMapper;
import com.produto.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    private ProdutoMapper produtoMapper = Mappers.getMapper(ProdutoMapper.class);

    public List<ProdutoDto> buscarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoDto> produtoDtos = new ArrayList<ProdutoDto>();
        for (Produto produto : produtos) {
            produtoDtos.add(produtoMapper.toDto(produto));
        }
        return produtoDtos;
    }

    public ProdutoDto buscarPorId(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produtoMapper.toDto(produto.get());
    }

    public ProdutoDto inserir(ProdutoDto produtoDto) {
        Produto produto = produtoMapper.toDomain(produtoDto);
        produto = produtoRepository.save(produto);
        return produtoMapper.toDto(produto);
    }

    public ProdutoDto atualizar(Integer id, ProdutoDto produtoDto) {
        produtoDto.setId(id);
        Produto produto = produtoMapper.toDomain(produtoDto);
        produto = produtoRepository.save(produto);
        return produtoMapper.toDto(produto);
    }

    public void deletar(Integer id) {
        produtoRepository.deleteById(id);
    }
}
