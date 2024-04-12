package com.modulos.vendas.dto.venda;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modulos.vendas.entities.Cliente;
import com.modulos.vendas.entities.Produto;
import com.modulos.vendas.entities.Vendedor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VendaResponse {
    private Integer id;
    private LocalDateTime data;
    private Double valor;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Vendedor vendedor;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Produto> produtos;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Cliente cliente;
}
