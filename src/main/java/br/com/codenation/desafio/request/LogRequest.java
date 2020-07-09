package br.com.codenation.desafio.request;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import br.com.codenation.desafio.enums.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="LogRequest", description="Defines a criteria to search logs")
public class LogRequest {

    @NotNull
    @ApiModelProperty(
        value = "The log's title",
        example = "Error on 127.0.0.1")
    private String title;

    @NotNull
    @ApiModelProperty(
        value = "The content or stacktrace of a log",
        example = "Java runtime error: null pointer exception")
    private String description;

    @NotNull
    @ApiModelProperty(
        value = "Which origin came from",
        example = "api xyz on 127.0.0.1:8080")
    private String origin;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Which environment came")
    private Environment environment;

    @NotNull
    @Enumerated
    @ApiModelProperty(value = "Which log level")
    private Level level;

	@CreatedDate
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @ApiModelProperty(value = "Which date/time use to starting searching")
    private LocalDateTime lastOccurrence;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Which log current status")
    private Status status;

    @NotNull
    @ApiModelProperty(value = "Which user must use to search")
    private UUID userId;

}
