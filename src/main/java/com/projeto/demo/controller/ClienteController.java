package com.projeto.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.demo.model.Cliente;
import com.projeto.demo.repository.ClienteRepository;

@Validated
@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*") //Aceita se comunicar com todos os dominios, no caso para se cominucar com o front.
public class ClienteController {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //Coloca o retorno do objeto no corpo da resposta em formato JSON e status 201.
    public Cliente salvarCliente(@RequestBody @Valid Cliente cliente){ //RequestBody faz o cliente passado no método ser o JSON no corpo do POST.
        return clienteRepository.save(cliente);
    }

    @GetMapping("{id}") //id pego na URL.
    public Cliente buscarClientePorId(@PathVariable Integer id){ //Joga o id pega na URL na variável id.
        return clienteRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)); //Se der erro retorna 404.
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //Sem objeto de retorno.
    public void deletarCliente(@PathVariable Integer id){
         clienteRepository.findById(id)
            .map(cliente -> { //Pega o cliente da consulta, deleta e retorna nada.
                clienteRepository.delete(cliente); 
                return Void.TYPE;
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@PathVariable Integer id, @Valid @RequestBody Cliente cliente){
        clienteRepository.findById(id)
            .map(clienteAtualizado -> { 
                cliente.setId(clienteAtualizado.getId()); //Cliente passado com id passado na URL é passado o cliente setado no corpo da requisição para o cliente buscado no DB.
                return clienteRepository.save(cliente);
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
