package br.com.codenation.desafio.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.validation.annotation.Validated;

import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import br.com.codenation.desafio.enums.Status;
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
@Table(name = "occurrences")
public class Ocurrence {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @ManyToOne
    private Log log;

	@CreatedDate
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dtCreated;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Environment environment;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Level level;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @OneToOne
    private User user;
    
}
