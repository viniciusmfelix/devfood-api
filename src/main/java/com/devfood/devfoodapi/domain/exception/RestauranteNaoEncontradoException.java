package com.devfood.devfoodapi.domain.exception;

@SuppressWarnings("serial")
public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException{

	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RestauranteNaoEncontradoException(Long id) {
		this(String.format("Restaurante de id %d não existe no registro.", id));
	}
}
