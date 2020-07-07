package br.com.codenation.desafio.dtos;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	@NotEmpty
	@Size(min = 3, max = 255)
	private String title;

	@NotEmpty
	private String origin;

	@NotEmpty
	private String description;

	private LocalDateTime lastOccurrence;

	private List<Ocurrence> occurrences;

	@NotNull
	private User user;
}
