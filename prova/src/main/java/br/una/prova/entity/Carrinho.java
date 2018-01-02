package br.una.prova.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



import java.util.Objects;
import java.util.Set;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;// utilizado para formatar datas

@Entity
public class Carrinho {
    @Id
    @GeneratedValue
    private Integer id;
    
    public String nomecarrinho;
    

    Timestamp fmt = new Timestamp(System.currentTimeMillis());
	String dataformatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(fmt.getTime());
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;


    @ManyToMany
    @JoinTable(name="carrinho_produto",
            joinColumns=@JoinColumn(name="carrinho_id"),
            inverseJoinColumns=@JoinColumn(name="produto_id")
    )
    
    private Set<Produto> produtos;
    
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomecarrinho() {
		return nomecarrinho;
	}

	public void setNomecarrinho(String nomecarrinho) {
		this.nomecarrinho = nomecarrinho;
	}

	public Timestamp getFmt() {
		return fmt;
	}

	public void setFmt(Timestamp fmt) {
		this.fmt = fmt;
	}

	public String getDataformatada() {
		return dataformatada;
	}

	public void setDataformatada(String dataformatada) {
		this.dataformatada = dataformatada;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrinho carrinho = (Carrinho) o;
        return Objects.equals(id, carrinho.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
        
}
