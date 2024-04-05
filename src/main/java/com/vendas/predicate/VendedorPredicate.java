package com.vendas.predicate;

import com.vendas.comum.predicate.PredicateBase;

import static com.vendas.entities.QVendedor.vendedor;

public class VendedorPredicate extends PredicateBase {
    public VendedorPredicate comId(Integer id) {
        if (id != null) {
            builder.and(vendedor.id.eq(id));
        }
        return this;
    }
}
