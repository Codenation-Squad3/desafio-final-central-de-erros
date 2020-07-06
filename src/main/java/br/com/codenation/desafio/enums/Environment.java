package br.com.codenation.desafio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum Environment {

	PRODUCTION("Produção"),
	TEST("Teste"),
	DEVELOPMENT("Desenvolvimento");
	
	private String description;

}
