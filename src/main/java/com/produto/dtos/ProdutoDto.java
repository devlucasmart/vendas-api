package com.produto.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.produto.entities.Categoria;

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
public class ProdutoDto {

    private Integer id;
    private String descricao;
    private LocalDateTime dataCadastro;
    private Double preco;

    @JsonInclude(Include.NON_NULL)
    private Categoria categoria;

}
