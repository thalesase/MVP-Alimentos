package com.mvp.alimentos.servico.implementacao;

import com.mvp.alimentos.modelo.Ingrediente;
import com.mvp.alimentos.repositorio.IngredienteRepositorio;
import com.mvp.alimentos.servico.IngredienteServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class IngredienteServicoImpl implements IngredienteServico {
    
    @Autowired
    IngredienteRepositorio ingredienteRepositorio;
    
    public Ingrediente obtemIngredientePeloNome(String nomeIngrediente) throws RuntimeException {
        Ingrediente ingrediente = ingredienteRepositorio.findByNome(nomeIngrediente);
        if (ObjectUtils.isEmpty(ingrediente)) {
            throw new RuntimeException("Nome do ingrediente não existe");
        }
        return ingrediente;
    }
}
