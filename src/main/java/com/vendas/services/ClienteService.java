package com.vendas.services;

import com.vendas.dto.cliente.ClienteRequest;
import com.vendas.dto.cliente.ClienteResponse;
import com.vendas.entities.Cliente;
import com.vendas.mappers.ClienteMapper;
import com.vendas.predicate.ClientePredicate;
import com.vendas.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;
    private ClienteMapper ClienteMapper = Mappers.getMapper(ClienteMapper.class);

    public List<ClienteResponse> findAll() {
        var cliente = repository.findAll();
        return ClienteMapper.toListResponse(cliente);
    }

    public ClienteResponse findById(Integer id) {
        var cliente = getById(id);
        return ClienteMapper.toResponse(cliente);
    }

    public ClienteResponse save(ClienteRequest request) {
        var cliente = ClienteMapper.toDomain(request);
        repository.save(cliente);
        return ClienteMapper.toResponse(cliente);
    }

    public ClienteResponse update(Integer id, ClienteRequest request) {
        var cliente = getById(id);
        var clienteUpdate = ClienteMapper.toDomain(request);
        clienteUpdate.setId(cliente.getId());

        repository.save(clienteUpdate);
        return ClienteMapper.toResponse(clienteUpdate);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private Boolean existsCliente(ClienteRequest request) {
        var cliente = new ClientePredicate()
                .comNome(request.getNome())
                .comCpf(request.getCpf())
                .comRg(request.getRg())
                .build();

        return repository.exists(cliente);
    }

    private Boolean existsCpf(Cliente request) {
        var cliente = new ClientePredicate()
                .comCpf(request.getCpf())
                .build();

        return repository.exists(cliente);
    }

    private Boolean existsRg(Cliente request) {
        var cliente = new ClientePredicate()
                .comRg(request.getRg())
                .build();

        return repository.exists(cliente);
    }

    private void validaEstruturaCliente(ClienteRequest request) {
        if (existsCliente(request)) {
            throw new ValidationException("Já Existe um Cliente cadastrado");
        }
    }

    private Cliente getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ValidationException("Cliente Não Ecnontrado!"));
    }
}
