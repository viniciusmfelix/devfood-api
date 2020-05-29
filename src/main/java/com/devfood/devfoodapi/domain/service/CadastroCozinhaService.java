package com.devfood.devfoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devfood.devfoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.devfood.devfoodapi.domain.exception.EntidadeEmUsoException;
import com.devfood.devfoodapi.domain.model.Cozinha;
import com.devfood.devfoodapi.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO = 
			"Cozinha de código %d não pode ser removida, pois está em uso.";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha buscarOuFalharPorId(Long id) {
		return cozinhaRepository.findById(id)
		.orElseThrow(() -> new CozinhaNaoEncontradaException(id));
	}
	
	public Cozinha adicionar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void remover(Long id) {
		try {
			cozinhaRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, id));
		}
	}
}
