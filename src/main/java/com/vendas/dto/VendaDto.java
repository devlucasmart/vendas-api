package com.vendas.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.vendas.entities.Cliente;
import com.vendas.entities.Produto;
import com.vendas.entities.Vendedor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendaDto {
    private Integer id;
    private LocalDateTime data;
    private Double valor;
    @JsonInclude(Include.NON_NULL)
    private Vendedor vendedor;
    @JsonInclude(Include.NON_NULL)
    private List<Produto> produtos;
    @JsonInclude(Include.NON_NULL)
    private Cliente cliente;
}
