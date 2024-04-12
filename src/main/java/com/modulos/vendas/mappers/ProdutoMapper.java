package com.modulos.vendas.mappers;

import com.modulos.vendas.dto.produto.ProdutoRequest;
import com.modulos.vendas.dto.produto.ProdutoResponse;
import com.modulos.vendas.entities.Produto;
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
