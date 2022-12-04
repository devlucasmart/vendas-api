package com.xbrain.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double valor;
    private Integer qtd;

    public Produto(){}

    public Produto(Integer id, String nome, Double valor, Integer qtd){
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.qtd = qtd;
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

    public Integer getQtd(){
        return this.qtd;
    }

    public void setQtd(Integer qtd){
        this.qtd = qtd;
    }    

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((qtd == null) ? 0 : qtd.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (qtd == null) {
			if (other.qtd != null)
				return false;
		} else if (!qtd.equals(other.qtd))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
    public String toString(){
        return "Produto: id=" + this.id + ", nome=" + this.nome + ", valor=" + this.valor;
    }
    
}
