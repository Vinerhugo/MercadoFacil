package br.una.prova.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.una.prova.entity.Carrinho;


public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {

	Page<Carrinho> findBynomecarrinhoLike(String nomecarrinho, Pageable pageable);
	
	
}
