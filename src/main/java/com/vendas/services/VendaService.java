package com.vendas.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendas.dtos.VendaDto;
import com.vendas.entities.Venda;
import com.vendas.mappers.VendaMapper;
import com.vendas.repositories.VendaRepository;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    private VendaMapper VendaMapper = Mappers.getMapper(VendaMapper.class);

    public List<VendaDto> buscarTodos() {
        var vendas = vendaRepository.findAll();
        var vendaDtos = new ArrayList<VendaDto>();
        for (Venda venda : vendas) {
            vendaDtos.add(VendaMapper.toDto(venda));
        }
        return vendaDtos;
    }

    public List<Venda> buscarTodosPorIdVendedorEPeriodo(Integer idVendedor, LocalDateTime inicio, LocalDateTime fim) {
        return vendaRepository.findByVendedorIdAndDataBetween(idVendedor, inicio, fim);
    }

    public VendaDto buscarPorId(Integer id) {
        var venda = vendaRepository.findById(id);
        return VendaMapper.toDto(venda.get());
    }

    public Long contarDias(LocalDate inicio, LocalDate fim) {
        return ChronoUnit.DAYS.between(inicio, fim);
    }

    public VendaDto inserir(VendaDto vendaDto) {
        var venda = VendaMapper.toDomain(vendaDto);
        venda = vendaRepository.save(venda);
        return VendaMapper.toDto(venda);
    }

    public VendaDto atualizar(Integer id, VendaDto vendaDto) {
        vendaDto.setId(id);
        var venda = VendaMapper.toDomain(vendaDto);
        venda = vendaRepository.save(venda);
        return VendaMapper.toDto(venda);
    }

    public void deletar(Integer id) {
        vendaRepository.deleteById(id);
    }
}
