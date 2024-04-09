package com.vendas.dto.vendedor;

import com.vendas.dto.VendaDto;
import lombok.Data;

import java.util.List;

@Data
public class VendedorResponse {
    private Integer id;
    private String nome;
    private List<VendaDto> vendas;
}
