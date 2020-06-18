package br.com.codenation.desafio.model;

import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import br.com.codenation.desafio.enums.Status;
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
public class Ocurrence {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @ManyToOne
    private Log log;

    @Column
    @NotNull
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

}
