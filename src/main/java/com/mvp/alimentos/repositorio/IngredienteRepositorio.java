package com.mvp.alimentos.repositorio;

import com.mvp.alimentos.modelo.Ingrediente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepositorio extends JpaRepository<Ingrediente, Long>{
    Ingrediente findByNome(String nomeIngrediente);
}
