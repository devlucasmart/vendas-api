package com.vendas.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.vendas.repositories.ClienteRepository;
import com.vendas.repositories.ProdutoRepository;
import com.vendas.repositories.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.vendas.dto.VendaDto;
import com.vendas.entities.Venda;
import com.vendas.mappers.VendaMapper;
import com.vendas.repositories.VendaRepository;

import javax.validation.ValidationException;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;
    private final ProdutoRepository produtoRepository;
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

    private void validarCampos(VendaDto venda){
        validarCliente(venda.getCliente().getId());
        validarVendedor(venda.getVendedor().getId());
    }

    private void validarCliente(Integer clienteId){
        clienteRepository.findById(clienteId).orElseThrow(() -> new ValidationException("Cliente não Encontrado!!"));
    }

    private void validarVendedor(Integer vendedorId){
        vendedorRepository.findById(vendedorId).orElseThrow(() -> new ValidationException("Vendedor não Encontrado!!"));
    }

    private void validarProduto(Integer produtoId){
        produtoRepository.findById(produtoId).orElseThrow(() -> new ValidationException("Produto não Encontrado"));
    }
}
