package com.devfood.devfoodapi.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devfood.devfoodapi.domain.model.Cozinha;
import com.devfood.devfoodapi.domain.model.Restaurante;
import com.devfood.devfoodapi.domain.repository.CozinhaRepository;
import com.devfood.devfoodapi.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/testes")
public class TestController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/procurar/todas-cozinhas")
	public List<Cozinha> listarPorNome(@RequestParam("nome") String nome){
		return cozinhaRepository.findTodasCozinhasByNomeContaining(nome);
	}
	
	@GetMapping("/procurar/unica-cozinha")
	public Optional<Cozinha> listarUnicaPorNome(String nome){
		return cozinhaRepository.findUnicaCozinhaByNome(nome);
	}
	
	@GetMapping("/procurar/restaurante-por-taxa-frete")
	public List<Restaurante> listarRestaurantePorFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("primeiro-restaurante-nome")
	public Optional<Restaurante> listarUnicoRestaurante(String nome, Long cozinhaId){
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
	}
	
	@GetMapping("/procurar/restaurante-containing")
	public List<Restaurante> listarTop2Restaurantes(String nome){
		return restauranteRepository.findTop2RestauranteByNomeContaining(nome);
	}
	
	@GetMapping("/procurar/exists-by-nome-cozinha")
	public boolean cozinhaExists(String nome) {
		return cozinhaRepository.existsByNome(nome);
	}
	
	@GetMapping("/procurar/exists-by-nome-restaurante")
	public boolean restauranteExists(String nome) {
		return restauranteRepository.existsByNome(nome);
	}
	
	@GetMapping("/procurar/com-frete-gratis")
	public List<Restaurante> freteGratis(String nome){
		return restauranteRepository.buscarPorFreteGratis(nome);
	}
}
