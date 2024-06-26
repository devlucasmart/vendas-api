package com.modulos.vendas.mappers;

import com.modulos.vendas.dto.cliente.ClienteRequest;
import com.modulos.vendas.dto.cliente.ClienteResponse;
import com.modulos.vendas.entities.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ClienteMapper {
    ClienteRequest toRequest(Cliente cliente);

    ClienteResponse toResponse(Cliente cliente);

    List<ClienteResponse> toListResponse(List<Cliente> cliente);

    Cliente toDomain(ClienteRequest request);
}
