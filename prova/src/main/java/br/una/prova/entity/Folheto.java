package br.una.prova.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Folheto {

	 @Id
	 @GeneratedValue
	 public Integer id;
	  
	 public String periodofolheto ;
	 
	 public String caminhofolheto ;
			  
			  
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPeriodofolheto() {
		return periodofolheto;
	}

	public void setPeriodofolheto(String periodofolheto) {
		this.periodofolheto = periodofolheto;
	}

	public String getCaminhofolheto() {
		return caminhofolheto;
	}

	public void setCaminhofolheto(String caminhofolheto) {
		this.caminhofolheto = caminhofolheto;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folheto folheto = (Folheto) o;
        return Objects.equals(id, folheto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

