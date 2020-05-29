package com.devfood.devfoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devfood.devfoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.devfood.devfoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.devfood.devfoodapi.domain.model.Cozinha;
import com.devfood.devfoodapi.domain.model.Restaurante;
import com.devfood.devfoodapi.domain.repository.CozinhaRepository;
import com.devfood.devfoodapi.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante buscarOuFalharPorId(Long id) {
		return restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradoException(id));
	}

	public Restaurante adicionar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha =  cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
		
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}

}
