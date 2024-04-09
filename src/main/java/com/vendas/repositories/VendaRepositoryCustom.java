package com.vendas.repositories;

import com.vendas.dto.venda.VendaRequest;

import java.util.List;

public interface VendaRepositoryCustom {
    List<Integer> findProdutosByVendas(VendaRequest request);
}
