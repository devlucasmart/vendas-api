package com.modulos.vendas.mappers;

import com.modulos.vendas.dto.venda.VendaRequest;
import com.modulos.vendas.dto.venda.VendaResponse;
import com.modulos.vendas.dto.VendaDto;
import com.modulos.vendas.entities.Venda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface VendaMapper {

    VendaDto toDto(Venda venda);

    Venda toDomain(VendaDto vendaDto);

    Venda toDomain(VendaRequest request);

    VendaResponse toResponse(Venda venda);

    @Mapping(target = "vendedor", ignore = true)
    VendaDto toDtoIgnoreVendedor(Venda venda);

    @Mapping(target = "vendedor", ignore = true)
    VendaResponse toResponseIgnoreVendedor(Venda venda);

    @Mapping(target = "cliente", ignore = true)
    VendaDto toDtoIgnoreCliente(Venda venda);
}