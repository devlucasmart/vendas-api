package com.vendas.dto.venda;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vendas.entities.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendaRequest {
    private LocalDateTime data;
    private Double valor;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Vendedor vendedor;
}
