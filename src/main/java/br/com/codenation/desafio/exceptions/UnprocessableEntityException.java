package br.com.codenation.desafio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public UnprocessableEntityException(Throwable cause) {
		super(cause);
	}
}
