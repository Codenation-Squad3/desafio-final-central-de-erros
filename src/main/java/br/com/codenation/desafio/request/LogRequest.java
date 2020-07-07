package br.com.codenation.desafio.request;

import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import br.com.codenation.desafio.enums.Status;
import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.model.Ocurrence;
import br.com.codenation.desafio.repository.LogRepository;
import br.com.codenation.desafio.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.xml.bind.v2.schemagen.xmlschema.Occurs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

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
