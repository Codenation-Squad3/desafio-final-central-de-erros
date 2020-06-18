package br.com.codenation.desafio.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_id_generator")
    @GenericGenerator( name = "log_id_generator", strategy = "br.com.codenation.desafio.config.LogIdGenerator")
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

    @OneToMany
    private List<Occurrence> occurrences;

    @NotNull
    @ManyToOne
    private User user;
}
