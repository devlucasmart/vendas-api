package com.vendas.dto.vendedor;

import lombok.Data;
@Data
public class VendedorVendasResponse {
    private Integer id;
    private String nome;
    private Integer totalVendas;
    private Double valorTotalVendas;
    private Double mediaDiaria;
}
