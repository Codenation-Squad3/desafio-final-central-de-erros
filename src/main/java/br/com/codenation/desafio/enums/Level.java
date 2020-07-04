package br.com.codenation.desafio.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum Level {

    ERROR("Erro"),
    WARNING("Warning"),
    DEBUG("Debug");

    private String description;

}
