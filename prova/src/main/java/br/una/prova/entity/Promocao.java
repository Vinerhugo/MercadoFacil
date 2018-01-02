package br.una.prova.entity;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Promocao {
	@Id
    @GeneratedValue
    private Integer id;
	
	public String periodopromocao;
	
	public String nomepromocao ;
	
	public String caminhopromocao ;
	

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

	public Integer getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPeriodopromocao() {
		return periodopromocao;
	}

	public void setPeriodopromocao(String periodopromocao) {
		this.periodopromocao = periodopromocao;
	}

	public String getNomepromocao() {
		return nomepromocao;
	}

	public void setNomepromocao(String nomepromocao) {
		this.nomepromocao = nomepromocao;
	}

	public String getCaminhopromocao() {
		return caminhopromocao;
	}

	public void setCaminhopromocao(String caminhopromocao) {
		this.caminhopromocao = caminhopromocao;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promocao promocao = (Promocao) o;
        return Objects.equals(id, promocao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
	
	

