package com.projeto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.demo.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Integer>{
    
}
