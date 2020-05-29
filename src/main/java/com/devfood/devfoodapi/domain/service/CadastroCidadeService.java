package com.devfood.devfoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devfood.devfoodapi.domain.exception.CidadeNaoEncontradaException;
import com.devfood.devfoodapi.domain.exception.EntidadeEmUsoException;
import com.devfood.devfoodapi.domain.model.Cidade;
import com.devfood.devfoodapi.domain.model.Estado;
import com.devfood.devfoodapi.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	
	private static final String MSG_CIDADE_EM_USO = 
			"Cidade de código %d não pode ser deletada pois está em uso.";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService estadoService;
	
	public Cidade buscarOuFalharPorId(Long id) {
		return cidadeRepository.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException(id));
	}

	public Cidade adicionar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		
		Estado estado = estadoService.buscarOuFalharPorId(estadoId);
		
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}
	
	public void remover(Long id) {
		try {
			cidadeRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, id));
		}
	}
}
