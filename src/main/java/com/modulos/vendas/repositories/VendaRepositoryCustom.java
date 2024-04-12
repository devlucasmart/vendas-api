package com.modulos.vendas.repositories;

import com.modulos.vendas.dto.venda.VendaRequest;

import java.util.List;

public interface VendaRepositoryCustom {
    List<Integer> findProdutosByVendas(VendaRequest request);
}
