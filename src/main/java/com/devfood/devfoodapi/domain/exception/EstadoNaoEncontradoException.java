package com.devfood.devfoodapi.domain.exception;

@SuppressWarnings("serial")
public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException{
	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format("Estado de id %d não existe na listagem.",estadoId));
	}
}
