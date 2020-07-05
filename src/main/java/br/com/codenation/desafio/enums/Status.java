package br.com.codenation.desafio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum Status {

    ARCHIVED("Arquivado"),
    ACTIVE("Ativo"),
    EXCLUDED("Excluído");

    private String description;

}
