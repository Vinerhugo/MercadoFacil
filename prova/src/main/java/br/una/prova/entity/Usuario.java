package br.una.prova.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import java.util.Objects;

@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private Integer id;
    
    @NotBlank(message = "Campo obrigatório")
    private String nomeuser;

    public boolean adm;

    public boolean desativar;
    
    @NotNull(message = "Campo obrigatório apenas numeros")
    public int senha; 
    

	public boolean isDesativar() {
		return desativar;
	}

	public void setDesativar(boolean desativar) {
		this.desativar = desativar;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeuser() {
		return nomeuser;
	}

	public void setNomeuser(String nomeuser) {
		this.nomeuser = nomeuser;
	}

	public boolean isAdm() {
		return adm;
	}

	public void setAdm(boolean adm) {
		this.adm = adm;
	}

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario user = (Usuario) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
