package br.com.codenation.desafio.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum Status {

    ARCHIVED("Arquivado"),
    ACTIVE("Ativo"),
    EXCLUDED("Excluído");

    private String description;

}
