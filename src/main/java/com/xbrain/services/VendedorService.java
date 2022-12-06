package com.xbrain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbrain.dto.VendedorDto;
import com.xbrain.entities.Vendedor;
import com.xbrain.mappers.VendedorMapper;
import com.xbrain.repositories.VendedorRepository;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    private VendedorMapper vendedorMapper = Mappers.getMapper(VendedorMapper.class);

    public List<VendedorDto> buscarTodos() {
        List<Vendedor> vendedors = vendedorRepository.findAll();
        List<VendedorDto> vendedorDtos = new ArrayList<VendedorDto>();
        for (Vendedor vendedor : vendedors) {
            vendedorDtos.add(vendedorMapper.toDto(vendedor));
        }
        return vendedorDtos;
    }

    public VendedorDto buscarPorId(Integer id) {
        Optional<Vendedor> vendedor = vendedorRepository.findById(id);
        return vendedorMapper.toDto(vendedor.get());
    }

    public VendedorDto inserir(VendedorDto vendedorDto) {
        Vendedor vendedor = vendedorMapper.toDomain(vendedorDto);
        vendedor = vendedorRepository.save(vendedor);
        return vendedorMapper.toDto(vendedor);
    }

    public VendedorDto atualizar(Integer id, VendedorDto vendedorDto) {
        vendedorDto.setId(id);
        Vendedor vendedor = vendedorMapper.toDomain(vendedorDto);
        vendedor = vendedorRepository.save(vendedor);
        return vendedorMapper.toDto(vendedor);
    }

    public void deletar(Integer id) {
        vendedorRepository.deleteById(id);
    }
}