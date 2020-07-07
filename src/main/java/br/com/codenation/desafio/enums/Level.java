package br.com.codenation.desafio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Level {

	ERROR("Erro"),
	WARNING("Warning"),
	DEBUG("Debug");
	
	private String description;

}
