package com.modulos.vendas.predicate;

import com.modulos.comum.predicate.PredicateBase;
import lombok.Data;

import java.time.LocalDateTime;

import static com.modulos.vendas.entities.QVenda.venda;

@Data
public class VendasPredicate extends PredicateBase {
    public VendasPredicate comId(Integer id) {
        if (id != null) {
            builder.and(venda.id.eq(id));
        }
        return this;
    }

    public VendasPredicate comData(LocalDateTime data) {
        if (data != null) {
            builder.and(venda.data.eq(data));
        }
        return this;
    }

    public VendasPredicate comPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        if (dataInicio != null && dataFim != null) {
            builder.and(venda.data.between(dataInicio, dataFim));
        }
        return this;
    }

    public VendasPredicate comValor(Double valor) {
        if (valor != null) {
            builder.and(venda.valor.eq(valor));
        }
        return this;
    }

    public VendasPredicate comVendedor(Integer vendedorId) {
        if (vendedorId != null) {
            builder.and(venda.vendedor.id.eq(vendedorId));
        }
        return this;
    }
}
