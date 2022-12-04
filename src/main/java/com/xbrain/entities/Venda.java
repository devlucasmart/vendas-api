package com.xbrain.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "venda")
public class Venda implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime data;
    private Double valor;
    private Vendedor vendedorId;
    public Venda(){}

    public Venda(Integer id, LocalDateTime data, Double valor, Vendedor vendedorId){
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.vendedorId = vendedorId;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public LocalDateTime getData(){
        return this.data;
    }

    public void setData(LocalDateTime data){
        this.data = data;
    }

    public Double getValor(){
        return this.valor;
    }

    public void setValor(Double valor){
        this.valor = valor;
    }

    public Vendedor getVendedorId(){
        return this.vendedorId;
    }

    public void setVendedorId(Vendedor vendedorId){
        this.vendedorId = vendedorId;
    }

    @Override
    public String toString(){
        return "Venda: id = " + this.id + ", Data da Venda = " + this.data + ", Valor da Venda = " + this.valor + ", ID do Vendedor = " + this.vendedorId;
    }

}
