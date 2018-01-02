package br.una.prova.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.una.prova.entity.Email;

public interface EmailRepository extends JpaRepository<Email, Integer>{

}
