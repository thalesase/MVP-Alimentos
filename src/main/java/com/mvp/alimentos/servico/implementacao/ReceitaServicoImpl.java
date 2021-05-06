package com.mvp.alimentos.servico.implementacao;
import java.util.ArrayList;
import java.util.List;

import com.mvp.alimentos.modelo.Ingrediente;
import com.mvp.alimentos.modelo.Receita;
import com.mvp.alimentos.repositorio.ReceitaRepositorio;
import com.mvp.alimentos.servico.IngredienteServico;
import com.mvp.alimentos.servico.ReceitaServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class ReceitaServicoImpl implements ReceitaServico {

    @Autowired
    IngredienteServico ingredienteServico;

    @Autowired
    ReceitaRepositorio receitaRepositorio;

    public Double calculaValorReceitaCardapio(String nomeReceita) throws RuntimeException {
        Receita receita = receitaRepositorio.findByNome(nomeReceita);

        if (ObjectUtils.isEmpty(receita)) {
            throw new RuntimeException("Nome da receita não existe");
        }

        return receita.calculaValorReceita();
    }

    public Double calculaValorReceitaPersonalizada(List<String> listaNomeIngredientes) throws RuntimeException {
        if (ObjectUtils.isEmpty(listaNomeIngredientes)) {
            throw new RuntimeException("Lista de ingredientes da receita está vazia");
        }
        
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        
        for (String nomeIngrediente : listaNomeIngredientes) {
            ingredientes.add(ingredienteServico.obtemIngredientePeloNome(nomeIngrediente));
        }
        
        return new Receita(ingredientes).calculaValorReceita();
    }

}