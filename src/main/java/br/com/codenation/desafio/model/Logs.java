package br.com.codenation.desafio.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "logs_challenge")
public class Logs {
	
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    
    @Generated(value = GenerationTime.ALWAYS)
    @Column(columnDefinition =
    	"VARCHAR NOT NULL DEFAULT MD5(" +
        " CONCAT(" +
        "    COALESCE(title, ''), " +
        "    COALESCE(log, ''), " +
        "    COALESCE(level, ''), " +
        "    COALESCE(source, ''), " +
        "    COALESCE(environment, '') " +
        ")" +
        ")")
    private String logHash;
    
    @NotNull
    @Size(min = 3, max = 255)
    private String title;
    
    @NotNull
    @Size(min = 3, max = 255)
    private String log;
    
    @NotNull
    private LocalDateTime datetime;
        
    @NotNull
    @Enumerated(EnumType.STRING)
    private Environment environment;
    
    @NotNull
    @Size(min = 3, max = 255)
    private String source;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Level level;
    
    @NotNull
    @ManyToOne
    private User user;
}
