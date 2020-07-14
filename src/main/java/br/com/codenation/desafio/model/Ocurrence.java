package br.com.codenation.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "occurrence")
public class Ocurrence {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @JsonIgnore
    @ManyToOne
    private Log log;

    @Column
    @NotNull
    private LocalDateTime created_at;

    @NotNull
    @OneToOne
    private User user;

}
