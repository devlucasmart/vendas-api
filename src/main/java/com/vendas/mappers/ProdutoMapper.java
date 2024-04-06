package com.vendas.mappers;

import com.vendas.dtos.produto.ProdutoRequest;
import com.vendas.dtos.produto.ProdutoResponse;
import com.vendas.entities.Produto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProdutoMapper {
    ProdutoRequest toRequest(Produto produto);

    ProdutoResponse toResponse(Produto produto);

    List<ProdutoResponse> toListResponse(List<Produto> Produto);

    Produto toDomain(ProdutoRequest request);
}
