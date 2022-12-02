package com.xbrain.entities;

public class Produto {

    private Integer id;
    private String nome;
    private Double valor;

    public Produto(){}

    public Produto(Integer id, String nome, Double valor){
        this.id = id;
        this.nome = nome;
        this.valor = valor;
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

    public Double getValor(){
        return this.valor;
    }

    public void setValor(Double valor){
        this.valor = valor;
    }

    @Override
    public String toString(){
        return "Produto: id=" + this.id + ", nome=" + this.nome + ", valor=" + this.valor;
    }
    
}
