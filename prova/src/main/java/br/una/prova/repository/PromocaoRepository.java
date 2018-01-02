package br.una.prova.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.una.prova.entity.Promocao;

public interface PromocaoRepository extends JpaRepository<Promocao, Integer>{

	Page<Promocao> findBynomepromocaoLike(String nomepromocao, Pageable pageable);

}
