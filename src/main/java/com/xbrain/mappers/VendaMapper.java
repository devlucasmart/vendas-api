package com.xbrain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.xbrain.dtos.VendaDto;
import com.xbrain.entities.Venda;

@Mapper
public interface VendaMapper {
    VendaDto toDto(Venda venda);

    Venda toDomain(VendaDto vendaDto);

    @Mapping(target = "vendedor", ignore = true)
    VendaDto toDtoIgnoreVendedor(Venda venda);
}
