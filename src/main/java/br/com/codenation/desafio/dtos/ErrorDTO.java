package br.com.codenation.desafio.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private int code;
    private Object message;
    private long timestamp;

}
