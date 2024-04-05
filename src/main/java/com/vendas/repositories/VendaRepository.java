package com.vendas.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.vendas.entities.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer>, QuerydslPredicateExecutor<Venda> {
    // select * from venda where vendedor_id = 1 and data between
    // '1996-02-24T00:00:00' and '1996-11-11T23:59:59';
    // select * from venda where vendedor_id = :idVendedor and data between :inicio
    // and :fim;
    public List<Venda> findByVendedorIdAndDataBetween(Integer idVendedor, LocalDateTime inicio, LocalDateTime fim);
}