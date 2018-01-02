package br.una.prova.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.una.prova.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Integer>{
	
	Page<Produto> findBynomeprodLike(String nomeprod, Pageable pageable);
	Page<Produto> findBycodbarraLike(Integer codbarra, Pageable pageable);
}
