package br.com.codenation.desafio.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum Environment {

	PRODUCTION("Produção"),
	TEST("Teste"),
	DEVELOPMENT("Desenvolvimento");
	
	private String description;
	
	Environment(String description) {
		this.description = description;
	}
	
	public String getDescricao() {
		return description;
	}
	
}
