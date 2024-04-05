package com.vendas.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vendas.dtos.VendaDto;
import com.vendas.entities.Venda;

@Mapper
public interface VendaMapper {

    VendaDto toDto(Venda venda);

    Venda toDomain(VendaDto vendaDto);

    @Mapping(target = "vendedor", ignore = true)
    VendaDto toDtoIgnoreVendedor(Venda venda);

}