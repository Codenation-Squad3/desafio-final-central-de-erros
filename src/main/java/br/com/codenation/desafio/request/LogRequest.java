package br.com.codenation.desafio.request;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

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

	@CreatedDate
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime lastOccurrence;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private UUID userId;

}
