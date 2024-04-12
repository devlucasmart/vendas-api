package com.modulos.vendas.dto.venda;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VendaFiltro {
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
