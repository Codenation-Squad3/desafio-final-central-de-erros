package br.com.codenation.desafio.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import br.com.codenation.desafio.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Table(name="log")
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_id_generator")
	@GenericGenerator(
			name = "log_id_generator",
			strategy = "br.com.codenation.desafio.config.LogIdGenerator",
			parameters = {
					@org.hibernate.annotations.Parameter(
							name = "use_as_title", 
							value = "title"),
					@org.hibernate.annotations.Parameter(
							name = "use_as_origin", 
							value = "origin"),
					@org.hibernate.annotations.Parameter(
							name = "use_as_description", 
							value = "description"),})
	private String id;

	@Column
	@NotNull
	@Size(min = 3, max = 255)
	private String title;
	
	@Column
	@NotNull
	private String origin;

	@Column
	@NotNull
	private String description;

	@Column
	@NotNull
	private LocalDateTime lastOccurrence;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "log")
	private List<Ocurrence> occurrences;

	@NotNull
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User user;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Environment environment;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Level level;
}
