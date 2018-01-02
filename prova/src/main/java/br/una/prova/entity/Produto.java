package br.una.prova.entity;

import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import java.util.Objects;


@Entity
public class Produto {
    @Id
    @GeneratedValue
    public Integer id;

    @NotBlank(message = "Campo obrigatório")
    public String nomeprod;

    @NotNull(message = "Campo obrigatório")
    public int codbarra;
    
    public double preco;
    
    public boolean desativar;
    
    public String caminhoimg;
    
	public String getCaminhoimg() {
		return caminhoimg;
	}

	public void setCaminhoimg(String caminhoimg) {
		this.caminhoimg = caminhoimg;
	}

	public Integer getId() {
		return id;
	}

	public boolean isDesativar() {
		return desativar;
	}

	public void setDesativar(boolean desativar) {
		this.desativar = desativar;
	}

	public void setIdprod(Integer id) {
		this.id = id;
	}

	public String getNomeprod() {
		return nomeprod;
	}

	public void setNomeprod(String nomeprod) {
		this.nomeprod = nomeprod;
	}

	public int getCodbarra() {
		return codbarra;
	}

	public void setCodbarra(int codbarra) {
		this.codbarra = codbarra;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
