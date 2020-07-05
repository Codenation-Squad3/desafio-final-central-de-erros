package br.com.codenation.desafio.mappers;

import br.com.codenation.desafio.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogResourceInput {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
}
