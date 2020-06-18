package br.com.codenation.desafio.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import br.com.codenation.desafio.annotation.CompoundPk;
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
public class Log {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_id_generator")
    @GenericGenerator(
            name = "log_id_generator",
            strategy = "br.com.codenation.desafio.config.LogIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "annotation_name", 
                            value = "CompoundPk")})
    private String id;

	// ?? use title ??
	/*
    @Column
    @NotNull
    @CompoundPk
    @Size(min = 3, max = 255)
    private String title;
	*/
	
    @Column
    @NotNull
    private String origin;

    @Column
    @NotNull
    private String description;

    @Column
    @NotNull
    private LocalDateTime lastOccurrence;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "log")
    private List<Ocurrence> occurrences;

    @NotNull
    @ManyToOne
    private User user;
}
