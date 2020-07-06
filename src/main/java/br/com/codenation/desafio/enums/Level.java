package br.com.codenation.desafio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum Level {

	ERROR("Erro"),
	WARNING("Warning"),
	DEBUG("Debug");
	
	private String description;

}
