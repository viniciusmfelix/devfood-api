package com.devfood.devfoodapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devfood.devfoodapi.domain.model.Cozinha;
import com.devfood.devfoodapi.domain.repository.CozinhaRepository;
import com.devfood.devfoodapi.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}
	
	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable("cozinhaId") Long id) {
		return cadastroCozinha.buscarOuFalharPorId(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinha.adicionar(cozinha);
	}
	
	@PutMapping("/{cozinhaId}")
	public Cozinha atualizar(@PathVariable("cozinhaId") Long id, @RequestBody Cozinha cozinha){
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalharPorId(id);
		
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		
		return cadastroCozinha.adicionar(cozinhaAtual);
		
	}

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("cozinhaId") Long id){
		cadastroCozinha.remover(id);
	}
}
