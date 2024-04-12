package com.modulos.vendas.mappers;

import com.modulos.vendas.dto.vendedor.VendedorRequest;
import com.modulos.vendas.dto.vendedor.VendedorResponse;
import com.modulos.vendas.entities.Vendedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.modulos.vendas.dto.VendedorDto;

@Mapper
public interface VendedorMapper {

    VendedorDto toDto(Vendedor vendedor);
    VendedorRequest toRequest(Vendedor vendedor);
    Vendedor toDomain(VendedorRequest request);
    VendedorResponse toResponse(Vendedor vendedor);

    @Mapping(target = "vendas", ignore = true)
    VendedorDto toDtoIgnoreVendas(Vendedor vendedor);

    @Mapping(target = "vendas", ignore = true)
    VendedorResponse toResponseIgnoreVendas(Vendedor vendedor);
}