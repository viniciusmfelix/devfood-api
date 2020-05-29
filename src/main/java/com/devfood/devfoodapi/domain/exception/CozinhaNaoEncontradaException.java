package com.devfood.devfoodapi.domain.exception;

@SuppressWarnings("serial")
public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException{

	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CozinhaNaoEncontradaException(Long cozinhaId) {
		this(String.format("Cozinha de código %d não existente.", cozinhaId));
	}
}
