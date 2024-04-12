package com.modulos.vendas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class VendedorVendasDto {

    private Integer id;
    private String nome;
    private Integer totalVendas;
    private Double valorTotalVendas;
    private Double mediaDiaria;
}
