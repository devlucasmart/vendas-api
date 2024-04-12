package com.modulos.vendas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.modulos.vendas.entities.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer>, QuerydslPredicateExecutor<Vendedor>{
    List<Vendedor> findAll();
}