package com.vendas.mappers;

import com.vendas.dto.produto.ProdutoRequest;
import com.vendas.dto.produto.ProdutoResponse;
import com.vendas.entities.Produto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProdutoMapper {
    Produto toDomain(ProdutoRequest request);
    ProdutoRequest toRequest(Produto produto);

    ProdutoResponse toResponse(Produto produto);

    List<ProdutoResponse> toListResponse(List<Produto> Produto);

//    @Mapping(target = "venda", ignore = true)
//    Produto toIgnoreVenda(Produto produto);
}
