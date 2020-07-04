package br.com.codenation.desafio.dtos.response;

import java.time.LocalDateTime;
import java.util.List;

import br.com.codenation.desafio.model.Ocurrence;
import br.com.codenation.desafio.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {

    private String id;
    private String title;
    private String origin;
    private String description;
    private LocalDateTime lastOccurrence;
    private List<Ocurrence> occurrences;
    private User user;
}
