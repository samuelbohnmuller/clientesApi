package com.projeto.demo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //Cria construtor, get e set.
@NoArgsConstructor //Cria construtor sem argumentos.
@AllArgsConstructor //Cria construtor.
public class Cliente {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto incrementa o id.
    private Integer id;
    @Column(nullable = false, length = 150) @NotBlank(message = "O campo nome é obrigatório") //Obrigatório.
    private String nome;
    @Column(nullable = false, length = 11) @NotBlank 
    private String cpf;
    @Column(name = "data_cadastro", updatable = false) @JsonFormat(pattern = "dd/MM/yyyy") //Nome no DB e formatado que nunca pode ser atualizado.
    
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist() {
        setDataCadastro(LocalDate.now()); //Quando iniciado a aplicação, o atributo recebe a data de hoje.
    }
}
