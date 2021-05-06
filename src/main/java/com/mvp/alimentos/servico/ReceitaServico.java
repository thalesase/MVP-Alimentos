package com.mvp.alimentos.servico;

import java.util.List;

public interface ReceitaServico {

    public Double calculaValorReceitaCardapio(String nomeReceita) throws RuntimeException;

    public Double calculaValorReceitaPersonalizada(List<String> listaNomeIngredientes) throws RuntimeException;


}
