package com.devfood.devfoodapi.api.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.devfood.devfoodapi.domain.exception.EntidadeEmUsoException;
import com.devfood.devfoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.devfood.devfoodapi.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno no sistema, tente novamente mais tarde. Caso o problema persistir, contate o administrador do sitema.";
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request){
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
		String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;
	
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		} else if(rootCause instanceof UnrecognizedPropertyException) {
			return handleUnrecognizedPropertyException((UnrecognizedPropertyException) rootCause, headers, status, request);
		} else if(rootCause instanceof IgnoredPropertyException) {
			return handleIgnoredPropertyException((IgnoredPropertyException) rootCause, headers, status, request);
		} else if(rootCause instanceof NoHandlerFoundException) {
			return handleNoHandlerFoundException((NoHandlerFoundException) rootCause, headers, status, request);
		}
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = "O corpo da requisicao esta invalido. Verifique o erro de sintaxe.";
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	} 
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, 
			WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, 
			WebRequest request){
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	public ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' recebeu o valor '%s' que e de um tipo invalido, corrija e informe um valor compativel com o tipo %s."
				, path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	public ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
	
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade informada '%s' nao corresponde a nenhuma propriedade presente na entidade '%s'",
				ex.getPropertyName(), ex.getReferringClass().getSimpleName());
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	public ResponseEntity<Object> handleIgnoredPropertyException(IgnoredPropertyException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade informada '%s' corresponde a uma propriedade em '%s' porem nao esta disponivel para alteracao neste tipo de requisicao.", ex.getPropertyName(), ex.getReferringClass().getSimpleName()); 
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, 
			WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(body == null) {
			body = Problem.builder()
				.title(status.getReasonPhrase())
				.status(status.value())
				.build();
		} else if (body instanceof String) {
			body = Problem.builder()
				.title((String) body)
				.status(status.value())
				.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType type, String detail) {
		return Problem.builder()
				.status(status.value())
				.type(type.getUri())
				.title(type.getTitle())
				.detail(detail);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
		String detail = String.format("O parametro de URL '%s' recebeu o valor '%s', que e de um tipo invalido. Corrija"
				+ " e informe novamente um parametro de tipo '%s'.", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = String.format("A URI '%s' informada nao possui nenhum recurso atrelado. Verifique a sintaxe e tente novamente.",ex.getRequestURL());
	
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request){
		
		BindingResult bindingResult = ex.getBindingResult();
		
		List<Problem.Field> problemFields = bindingResult.getFieldErrors().stream()
				.map(fieldError -> Problem.Field.builder()
						.name(fieldError.getField())
						.userMessage(fieldError.getDefaultMessage())
						.build())
				.collect(Collectors.toList());
		
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		String detail = "Erro durante a validacao de propriedades da entidade.";
	
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage("Erro durante a validacao dos dados informados")
				.fields(problemFields)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
}
