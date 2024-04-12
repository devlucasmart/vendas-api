package com.modulos.vendas.predicate;

import com.modulos.comum.predicate.PredicateBase;

import static com.modulos.vendas.entities.QVendedor.vendedor;

public class VendedorPredicate extends PredicateBase {
    public VendedorPredicate comId(Integer id) {
        if (id != null) {
            builder.and(vendedor.id.eq(id));
        }
        return this;
    }
}
