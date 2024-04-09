package com.vendas.repositories;

import com.vendas.dto.venda.VendaRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

@SuppressWarnings("PMD.TooManyStaticImports")
public class VendaRepositoryImpl implements VendaRepositoryCustom{

    @Autowired
    private EntityManager entityManager;
    @Override
    public List<Integer> findProdutosByVendas(VendaRequest request) {
        return null;
    }
}
