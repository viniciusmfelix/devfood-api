package com.devfood.devfoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devfood.devfoodapi.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
