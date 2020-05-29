package com.devfood.devfoodapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.devfood.devfoodapi.domain.model.Restaurante;

public interface CustomizedRestauranteRepository{

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<Restaurante> buscarPorFreteGratis(String nome);

}