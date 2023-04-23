package com.projeto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.demo.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    
}
