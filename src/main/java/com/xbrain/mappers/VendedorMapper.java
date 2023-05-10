package com.xbrain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.xbrain.dtos.VendedorDto;
import com.xbrain.entities.Vendedor;

@Mapper
public interface VendedorMapper {

    VendedorDto toDto(Vendedor vendedor);

    Vendedor toDomain(VendedorDto vendedorDto);

    @Mapping(target = "vendas", ignore = true)
    VendedorDto toDtoIgnoreVendas(Vendedor vendedor);

}