package com.vendas.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class VendedorVendasDto {

    private Integer id;
    private String nome;
    private Integer totalVendas;
    private Double valorTotalVendas;
    private Double mediaDiaria;

}
