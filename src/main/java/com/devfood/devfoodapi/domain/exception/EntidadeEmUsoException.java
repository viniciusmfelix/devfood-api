package com.devfood.devfoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
@SuppressWarnings("serial")
public class EntidadeEmUsoException extends NegocioException{

	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}
}