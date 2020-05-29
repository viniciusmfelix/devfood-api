package com.devfood.devfoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade nao encontrada."),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso."),
	ERRO_NEGOCIO("/regra-violada", "Regra de negocio violada"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensivel"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parametro de URL invalido."),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso de URI nao existente na API."),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro interno de sistema.");
	
	private String title;
	private String uri;

	ProblemType(String path, String title) {
		this.uri = "https://devfood.api" + path;
		this.title = title;
	}
}
