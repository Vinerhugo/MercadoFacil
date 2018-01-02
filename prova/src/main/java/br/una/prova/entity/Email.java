package br.una.prova.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Email {

	@Id
    @GeneratedValue
    private Integer id;
	
    @NotBlank(message = "Campo obrigatório")
    public String nome;
	
    public String assunto;
    
    public Integer telefone;
    
    @NotBlank(message = "Campo obrigatório")
    public String email;
    
    public String message;
   
	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
