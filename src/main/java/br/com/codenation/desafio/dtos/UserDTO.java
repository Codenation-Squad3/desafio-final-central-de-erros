package br.com.codenation.desafio.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
	
    @NotNull
    @Size(min = 3, max = 120)
	private String nome;
    
    @NotNull
    @Email
    @Size(min = 5, max = 60)
    private String email;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy@HH:mm")
    private LocalDateTime createdAt;
}