package br.com.codenation.desafio.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LogDTO {

	private String title;
	private String origin;
	private String description;
	private LocalDateTime lastOccurrence;
}
