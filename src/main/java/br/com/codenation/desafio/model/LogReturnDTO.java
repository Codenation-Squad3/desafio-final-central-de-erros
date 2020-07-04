package br.com.codenation.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogReturnDTO {

    private Ocurrence ocurrence;
    private Log log;

}
