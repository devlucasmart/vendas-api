package com.modulos.vendas.dto.venda;

import com.modulos.vendas.entities.Cliente;
import com.modulos.vendas.entities.Produto;
import com.modulos.vendas.entities.Vendedor;
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
