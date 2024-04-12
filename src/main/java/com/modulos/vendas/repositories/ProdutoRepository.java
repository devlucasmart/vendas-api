package com.modulos.vendas.repositories;

import com.modulos.vendas.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>, QuerydslPredicateExecutor<Produto> {
}
