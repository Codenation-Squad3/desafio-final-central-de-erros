package br.com.codenation.desafio.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private UUID id;

	@Size(min = 3, max = 120)
	@NotEmpty
	private String nome;

	@Email
	@NotEmpty
	@Size(min = 5, max = 60)
	private String email;

	@NotEmpty
	@Size(min = 6, max = 60)
	private String password;
	
	private LocalDateTime createdAt;
}
