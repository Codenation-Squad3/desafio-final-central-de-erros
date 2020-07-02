package br.com.codenation.desafio.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum Status {

    ARCHIVED("Arquivado"),
    ACTIVE("Ativo"),
    EXCLUDED("Excluído");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
