package com.vendas.dto.venda;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vendas.entities.Cliente;
import com.vendas.entities.Produto;
import com.vendas.entities.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendaRequest {
    private LocalDateTime data;
    private Double valor;
    private Vendedor vendedor;
    private List<Produto> produtos;
    private Cliente cliente;
}
