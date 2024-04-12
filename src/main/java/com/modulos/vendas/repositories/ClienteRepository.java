package com.modulos.vendas.repositories;

import com.modulos.vendas.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>, QuerydslPredicateExecutor<Cliente> {
}
