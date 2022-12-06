package com.xbrain.mappers;

import org.mapstruct.Mapper;

import com.xbrain.dto.VendaDto;
import com.xbrain.entities.Venda;

@Mapper
public interface VendaMapper {
    VendaDto toDto(Venda venda);

    Venda toDomain(VendaDto vendaDto);
}
