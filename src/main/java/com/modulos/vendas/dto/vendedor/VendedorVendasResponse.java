package com.modulos.vendas.dto.vendedor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendedorVendasResponse {
    private Integer id;
    private String nome;
    private Integer totalVendas;
    @NumberFormat(pattern = "#,##0.00")
    private Double valorTotalVendas;
    private Double mediaDiaria;
}
