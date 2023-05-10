package com.xbrain.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbrain.dtos.VendaDto;
import com.xbrain.dtos.VendedorDto;
import com.xbrain.dtos.VendedorVendasDto;
import com.xbrain.entities.Venda;
import com.xbrain.entities.Vendedor;
import com.xbrain.mappers.VendaMapper;
import com.xbrain.mappers.VendedorMapper;
import com.xbrain.repositories.VendedorRepository;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private VendaService vendaService;

    private VendedorMapper vendedorMapper = Mappers.getMapper(VendedorMapper.class);
    private VendaMapper vendaMapper = Mappers.getMapper(VendaMapper.class);

    public List<VendedorDto> buscarTodos() {
        List<Vendedor> vendedors = vendedorRepository.findAll();
        List<VendedorDto> vendedorDtos = new ArrayList<VendedorDto>();
        for (Vendedor vendedor : vendedors) {
            List<Venda> vendas = vendedor.getVendas();
            List<VendaDto> vendasDto = new ArrayList<VendaDto>();
            for (Venda venda : vendas) {
                vendasDto.add(vendaMapper.toDtoIgnoreVendedor(venda));
            }
            VendedorDto vendedorDto = vendedorMapper.toDtoIgnoreVendas(vendedor);
            vendedorDto.setVendas(vendasDto);
            vendedorDtos.add(vendedorDto);
        }
        return vendedorDtos;
    }

    public VendedorDto buscarPorId(Integer id) {
        Optional<Vendedor> vendedor = vendedorRepository.findById(id);
        return vendedorMapper.toDto(vendedor.get());
    }

    public List<VendedorVendasDto> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        LocalDateTime inicioDateTime = LocalDateTime.of(inicio, LocalTime.of(0, 0, 0));
        LocalDateTime fimDateTime = LocalDateTime.of(fim, LocalTime.of(23, 59, 59));
        List<Vendedor> vendedores = vendedorRepository.findAll();
        List<VendedorVendasDto> vendedorVendasDto = new ArrayList<VendedorVendasDto>();

        for (Vendedor vendedor : vendedores) {
            List<Venda> vendas = vendaService.buscarTodosPorIdVendedorEPeriodo(vendedor.getId(), inicioDateTime,
                    fimDateTime);
            VendedorVendasDto vendasVendedorDto = new VendedorVendasDto();
            vendasVendedorDto.setId(vendedor.getId());
            vendasVendedorDto.setNome(vendedor.getNome());
            vendasVendedorDto.setTotalVendas(vendas.size());
            vendasVendedorDto
                    .setMediaDiaria(Double.parseDouble(vendas.size() + "") / vendaService.contarDias(inicio, fim));
            Double valor = 0.0;
            for (Venda venda : vendas) {
                valor = valor + venda.getValor();
            }
            vendasVendedorDto.setValorTotalVendas(valor);
            vendedorVendasDto.add(vendasVendedorDto);
        }
        return vendedorVendasDto;
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