package br.una.prova.repository;

import br.una.prova.entity.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

	Page<Usuario> findBynomeuserLike(String nomeuser, Pageable pageable);
}
