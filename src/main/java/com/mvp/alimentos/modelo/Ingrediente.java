package com.mvp.alimentos.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Ingrediente {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Double valor;

    @ManyToMany(mappedBy = "listaIngredientes")
    private List<Receita> listaReceitas;

    public Ingrediente() {
    }

    public Ingrediente(Long id, String nome, Double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }
    public Double getValor() {
        return valor;
    }    
}