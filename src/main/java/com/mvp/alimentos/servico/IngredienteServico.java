package com.mvp.alimentos.servico;

import com.mvp.alimentos.modelo.Ingrediente;

public interface IngredienteServico {

    public Ingrediente obtemIngredientePeloNome(String nomeIngrediente) throws RuntimeException;


}
