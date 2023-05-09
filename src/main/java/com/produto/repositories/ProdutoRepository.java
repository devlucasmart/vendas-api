package com.produto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.produto.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}