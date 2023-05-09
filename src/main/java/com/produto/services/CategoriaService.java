package com.produto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produto.dtos.ProdutoDto;
import com.produto.dtos.CategoriaDto;
import com.produto.entities.Produto;
import com.produto.entities.Categoria;
import com.produto.mappers.ProdutoMapper;
import com.produto.mappers.CategoriaMapper;
import com.produto.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private CategoriaMapper categoriaMapper = Mappers.getMapper(CategoriaMapper.class);
    private ProdutoMapper produtoMapper = Mappers.getMapper(ProdutoMapper.class);

    public List<CategoriaDto> buscarTodos() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaDto> categoriaDtos = new ArrayList<CategoriaDto>();
        for (Categoria categoria : categorias) {
            List<Produto> produtos = categoria.getProdutos();
            List<ProdutoDto> produtosDto = new ArrayList<ProdutoDto>();
            for (Produto produto : produtos) {
                produtosDto.add(produtoMapper.toDtoIgnoreCategoria(produto));
            }
            CategoriaDto categoriaDto = categoriaMapper.toDtoIgnoreProdutos(categoria);
            categoriaDto.setProdutos(produtosDto);
            categoriaDtos.add(categoriaDto);
        }
        return categoriaDtos;
    }

    public CategoriaDto buscarPorId(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoriaMapper.toDto(categoria.get());
    }

    public CategoriaDto inserir(CategoriaDto categoriaDto) {
        Categoria categoria = categoriaMapper.toDomain(categoriaDto);
        categoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDto(categoria);
    }

    public CategoriaDto atualizar(Integer id, CategoriaDto categoriaDto) {
        categoriaDto.setId(id);
        Categoria categoria = categoriaMapper.toDomain(categoriaDto);
        categoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDto(categoria);
    }

    public void deletar(Integer id) {
        categoriaRepository.deleteById(id);
    }
}