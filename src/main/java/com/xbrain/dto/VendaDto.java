package com.xbrain.dto;

import java.time.LocalDateTime;

import com.xbrain.entities.Vendedor;

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
public class VendaDto {

    private Integer id;
    private LocalDateTime data;
    private Double valor;
    private Vendedor vendedor;
    // private List<Produto> produtos;

}
