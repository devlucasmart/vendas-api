package com.vendas.mappers;

import com.vendas.dtos.cliente.ClienteRequest;
import com.vendas.dtos.cliente.ClienteResponse;
import com.vendas.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ClienteMapper {
    ClienteRequest toRequest(Cliente cliente);

    ClienteResponse toResponse(Cliente cliente);

    List<ClienteResponse> toListResponse(List<Cliente> cliente);

    Cliente toDomain(ClienteRequest request);
}
