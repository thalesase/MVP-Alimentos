package com.mvp.alimentos.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import org.springframework.util.ObjectUtils;

@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String nome;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name = "receita_ingrediente", 
        joinColumns = @JoinColumn(name = "id_receita"), 
        inverseJoinColumns = @JoinColumn(name = "id_ingrediente"))
    private List<Ingrediente> listaIngredientes;

    Receita(){}

    public Receita(int id, String nome, List<Ingrediente> listaIngredientes) {
        this.id = id;
        this.nome = nome;
        this.listaIngredientes = listaIngredientes;
    }

    public Receita(List<Ingrediente> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }

    public String getNome() {
        return nome;
    }

    public Double calculaValorReceita() throws RuntimeException {
        Double total = 0.0;

        if (ObjectUtils.isEmpty(listaIngredientes)) {
            throw new RuntimeException("Lista de ingredientes da receita está vazia");
        }

        total = calculaValorTotalSemDescontos();
        total = aplicaDescontos(total);

        return total;
    }

    private Double calculaValorTotalSemDescontos() {
        Double total = 0.0;
        for (Ingrediente ingrediente : listaIngredientes) {
            total += ingrediente.getValor();
        }
        return total;
    }

    private Double aplicaDescontos(Double total) {
        total-=calculaDescontosMuitaPromocao("Queijo");
        total-=calculaDescontosMuitaPromocao("Hamburguer");
        total-=calculaDescontosLight(total);
        return total;
    }

    private Double calculaDescontosMuitaPromocao(String nomeIngrediente) {
        int quantidadeIngrediente = 0;
        Double valorIngrediente = 0.0;

        for (Ingrediente ingrediente : listaIngredientes) {
            if(ingrediente.getNome().equals(nomeIngrediente)) {
                quantidadeIngrediente++;
                valorIngrediente = ingrediente.getValor();
            }
        }

        return quantidadeIngrediente/3 * valorIngrediente;
    }

    private Double calculaDescontosLight(Double total) {
        int quantidadeAlface = 0;
        int quantidadeBacon = 0;

        for (Ingrediente ingrediente : listaIngredientes) {
            if(ingrediente.getNome().equals("Alface")) {
                quantidadeAlface++;
            }

            if(ingrediente.getNome().equals("Bacon")) {
                quantidadeBacon++;
            }
        }

        return quantidadeAlface > 0 && quantidadeBacon == 0 ? total * 0.1 : 0;
    }
}