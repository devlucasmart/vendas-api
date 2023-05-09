package com.produto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.produto.dtos.CategoriaDto;
import com.produto.entities.Categoria;

@Mapper
public interface CategoriaMapper {

    CategoriaDto toDto(Categoria categoria);

    Categoria toDomain(CategoriaDto categoriaDto);

    @Mapping(target = "produtos", ignore = true)
    CategoriaDto toDtoIgnoreProdutos(Categoria categoria);

}