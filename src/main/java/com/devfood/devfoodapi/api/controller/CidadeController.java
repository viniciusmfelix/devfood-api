package com.devfood.devfoodapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devfood.devfoodapi.domain.exception.CidadeNaoEncontradaException;
import com.devfood.devfoodapi.domain.exception.EstadoNaoEncontradoException;
import com.devfood.devfoodapi.domain.exception.NegocioException;
import com.devfood.devfoodapi.domain.model.Cidade;
import com.devfood.devfoodapi.domain.repository.CidadeRepository;
import com.devfood.devfoodapi.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cidadeService;
	
	@GetMapping
	public List<Cidade> listar(){
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable("cidadeId") Long id) {
		try{
			return cidadeService.buscarOuFalharPorId(id);
		}catch(CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade) {
		try{
			return cidadeService.adicionar(cidade);
		}catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{cidadeId}")
	public ResponseEntity<?> atualizar(@PathVariable("cidadeId") Long id, @RequestBody Cidade cidade) {
		
		Cidade cidadeAtual = cidadeService.buscarOuFalharPorId(id);
		
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		
		
		try{
			cidadeService.adicionar(cidadeAtual);
			return ResponseEntity.ok(cidadeAtual);
		}catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}

	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("cidadeId") Long id){
		cidadeService.remover(id);
	}
	
}
