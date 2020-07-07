package br.com.codenation.desafio.exceptions;

public class ObjectCreationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectCreationException(String msg) {
		super(msg);
	}
}