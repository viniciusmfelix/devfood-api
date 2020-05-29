package com.devfood.devfoodapi.domain.exception;

@SuppressWarnings("serial")
public class NegocioException extends RuntimeException{
	public NegocioException(String mensagem) {
		super(mensagem);
	}
	
	public NegocioException(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
}
