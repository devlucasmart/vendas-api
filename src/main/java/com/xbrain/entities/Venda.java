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
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime data;
    private Double valor;
    private Vendedor vendedorId;

    public Venda() {
    }

    public Venda(Integer id, LocalDateTime data, Double valor, Vendedor vendedorId) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.vendedorId = vendedorId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Double getValor() {
        return this.valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Vendedor getVendedorId() {
        return this.vendedorId;
    }

    public void setVendedorId(Vendedor vendedorId) {
        this.vendedorId = vendedorId;
    }    

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result + ((vendedorId == null) ? 0 : vendedorId.hashCode());
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
		Venda other = (Venda) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		if (vendedorId == null) {
			if (other.vendedorId != null)
				return false;
		} else if (!vendedorId.equals(other.vendedorId))
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "Venda: id = " + this.id + ", Data da Venda = " + this.data + ", Valor da Venda = " + this.valor
                + ", ID do Vendedor = " + this.vendedorId;
    }

}
