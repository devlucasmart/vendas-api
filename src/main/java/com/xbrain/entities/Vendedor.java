package com.xbrain.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "vendedor")
public class Vendedor implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    public Vendedor(){}

    public Vendedor(Integer id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    @Override
    public String toString(){
        return "Venda: id" + this.id + ", Nome =" + this.nome;
    }
}
