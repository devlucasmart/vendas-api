package com.xbrain.mappers;

import org.mapstruct.Mapper;

import com.xbrain.dto.VendedorDto;
import com.xbrain.entities.Vendedor;

@Mapper
public interface VendedorMapper {
    VendedorDto toDto(Vendedor vendedor);

    Vendedor toDomain(VendedorDto vendedorDto);
}
