package com.modulos.vendas.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.modulos.vendas.entities.Cliente;
import com.modulos.vendas.entities.Produto;
import com.modulos.vendas.entities.Vendedor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendaDto {
    private Integer id;
    private LocalDateTime data;
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private Double valor;
    @JsonInclude(Include.NON_NULL)
    private Vendedor vendedor;
    @JsonInclude(Include.NON_NULL)
    private List<Produto> produtos;
    @JsonInclude(Include.NON_NULL)
    private Cliente cliente;
}
