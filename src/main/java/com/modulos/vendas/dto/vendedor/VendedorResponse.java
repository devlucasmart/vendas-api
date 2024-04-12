package com.modulos.vendas.dto.vendedor;

import com.modulos.vendas.dto.venda.VendaResponse;
import lombok.Data;

import java.util.List;

@Data
public class VendedorResponse {
    private Integer id;
    private String nome;
    private List<VendaResponse> vendas;
}
