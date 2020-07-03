package br.com.codenation.desafio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.codenation.desafio.dtos.ErrorDTO;
import javassist.NotFoundException;

@ControllerAdvice
public class ExceptionController {
	
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorDTO handleNotFoundException(NotFoundException exception) {
        return ErrorDTO.builder()
                .message(exception.getMessage())
                .code(HttpStatus.NOT_FOUND.value())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO handleConstraintValidationException(MethodArgumentNotValidException exception) {
        return ErrorDTO.builder()
                .message(getMessages(exception))
                .code(HttpStatus.NOT_FOUND.value())
                .timestamp(System.currentTimeMillis())
                .build();
    }
    
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorDTO handleDataIntegrityValidationException(DataIntegrityViolationException exception) {
        return ErrorDTO.builder()
                .message(getMessages(exception))
                .code(HttpStatus.NOT_FOUND.value())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    private List<String> getMessages(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors()
                .stream().map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.toList());
    }
    
    private String getMessages(DataIntegrityViolationException exception) {
        return exception.getMessage();
    }

}