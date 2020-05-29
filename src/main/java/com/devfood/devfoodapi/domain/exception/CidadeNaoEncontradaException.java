package com.devfood.devfoodapi.domain.exception;

@SuppressWarnings("serial")
public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException{

	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CidadeNaoEncontradaException(Long cidadeId) {
		this(String.format("Cidade de código %d não existe no registro de cidades.", cidadeId));
	}
}
