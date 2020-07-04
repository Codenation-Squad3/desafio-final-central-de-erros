package br.com.codenation.desafio.dtos.request;

import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import br.com.codenation.desafio.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogRequestDTO {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String origin;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Environment environment;

    @NotNull
    @Enumerated
    private Level level;

    @NotNull
    private LocalDateTime lastOccurrence;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private UUID userId;

}
