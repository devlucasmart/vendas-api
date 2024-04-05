package com.vendas.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vendas.dtos.VendedorDto;
import com.vendas.entities.Vendedor;

@Mapper
public interface VendedorMapper {

    VendedorDto toDto(Vendedor vendedor);

    Vendedor toDomain(VendedorDto vendedorDto);

    @Mapping(target = "vendas", ignore = true)
    VendedorDto toDtoIgnoreVendas(Vendedor vendedor);

}