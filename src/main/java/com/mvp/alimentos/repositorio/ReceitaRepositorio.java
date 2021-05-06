package com.mvp.alimentos.repositorio;

import com.mvp.alimentos.modelo.Receita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepositorio extends JpaRepository<Receita, Long>{
    Receita findByNome(String nomeReceita);
}
