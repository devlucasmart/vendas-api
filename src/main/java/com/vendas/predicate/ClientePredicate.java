package com.vendas.predicate;

import com.vendas.comum.predicate.PredicateBase;
import org.apache.commons.lang3.StringUtils;

import static com.vendas.entities.QCliente.cliente;

public class ClientePredicate extends PredicateBase {
    public ClientePredicate comId(Integer id) {
        if (id != null) {
            builder.and(cliente.id.eq(id));
        }
        return this;
    }

    public ClientePredicate comNome(String nome) {
        if (StringUtils.isNotBlank(nome)) {
            builder.and(cliente.nome.eq(nome));
        }
        return this;
    }

    public ClientePredicate comCpf(String cpf) {
        if (StringUtils.isNotBlank(cpf)) {
            builder.and(cliente.cpf.eq(cpf));
        }
        return this;
    }

    public ClientePredicate comRg(String rg) {
        if (StringUtils.isNotBlank(rg)) {
            builder.and(cliente.rg.eq(rg));
        }
        return this;
    }
}
