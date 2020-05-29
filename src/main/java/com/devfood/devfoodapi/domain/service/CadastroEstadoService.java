package com.devfood.devfoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devfood.devfoodapi.domain.exception.EntidadeEmUsoException;
import com.devfood.devfoodapi.domain.exception.EstadoNaoEncontradoException;
import com.devfood.devfoodapi.domain.model.Estado;
import com.devfood.devfoodapi.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private static final String MSG_ESTADO_EM_USO = 
			"Estado de id %d não pode ser excluído pois está sendo utilizado.";
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado buscarOuFalharPorId(Long id) {
		return estadoRepository.findById(id).orElseThrow(() -> new EstadoNaoEncontradoException(id));
	}
	
	public Estado adicionar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void remover(Long id) {
		try {
			estadoRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, id));
		}
	}
}
