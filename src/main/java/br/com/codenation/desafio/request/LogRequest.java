package br.com.codenation.desafio.request;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import br.com.codenation.desafio.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogRequest {

    @NotEmpty
    @Size(min = 3, max = 255)
    private String title;

    @NotEmpty
    private String description;

    @NotEmpty
    private String origin;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Environment environment;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Level level;

    private LocalDateTime lastOccurrence;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private UUID userId;

}
