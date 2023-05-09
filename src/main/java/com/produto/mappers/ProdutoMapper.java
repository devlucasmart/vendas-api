package com.produto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.produto.dtos.ProdutoDto;
import com.produto.entities.Produto;

@Mapper
public interface ProdutoMapper {

    ProdutoDto toDto(Produto produto);

    Produto toDomain(ProdutoDto produtoDto);

    @Mapping(target = "categoria", ignore = true)
    ProdutoDto toDtoIgnoreCategoria(Produto produto);

}