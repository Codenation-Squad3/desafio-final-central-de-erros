package br.com.codenation.desafio.model;

import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import br.com.codenation.desafio.enums.Status;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OccurrenceTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void occurrenceEnvironmentIsNotValid() {
        User user = User.builder()
                .email("jonh.doe@gmail.com")
                .nome("Jonh Doe")
                .password("123456")
                .build();

        Log log = Log.builder()
                .lastOccurrence(LocalDateTime.now())
                .origin("168.122.0.2")
                .description("Error description")
                .title("Error title")
                .user(user)
                .build();

        Occurrence occurrence = Occurrence.builder()
               .environment(null)
                .level(Level.DEBUG)
                .log(log)
                .status(Status.ARQUIVADO)
                .timestamp(LocalDateTime.now())
                .build();

        Set<ConstraintViolation<Occurrence>> constraintViolations = validator
                .validate(occurrence);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void occurrenceTimestampIsNotValid() {
        User user = User.builder()
                .email("jonh.doe@gmail.com")
                .nome("Jonh Doe")
                .password("123456")
                .build();

        Log log = Log.builder()
                .lastOccurrence(LocalDateTime.now())
                .origin("168.122.0.2")
                .description("Error description")
                .title("Error title")
                .user(user)
                .build();

        Occurrence occurrence = Occurrence.builder()
                .environment(Environment.DEVELOPMENT)
                .level(Level.DEBUG)
                .log(log)
                .status(Status.ARQUIVADO)
                .timestamp(null)
                .build();

        Set<ConstraintViolation<Occurrence>> constraintViolations = validator
                .validate(occurrence);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void occurrenceLevelIsNotValid() {
        User user = User.builder()
                .email("jonh.doe@gmail.com")
                .nome("Jonh Doe")
                .password("123456")
                .build();

        Log log = Log.builder()
                .lastOccurrence(LocalDateTime.now())
                .origin("168.122.0.2")
                .description("Error description")
                .title("Error title")
                .user(user)
                .build();

        Occurrence occurrence = Occurrence.builder()
                .environment(Environment.DEVELOPMENT)
                .level(null)
                .log(log)
                .status(Status.ARQUIVADO)
                .timestamp(LocalDateTime.now())
                .build();

        Set<ConstraintViolation<Occurrence>> constraintViolations = validator
                .validate(occurrence);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void occurrenceStatusIsNotValid() {
        User user = User.builder()
                .email("jonh.doe@gmail.com")
                .nome("Jonh Doe")
                .password("123456")
                .build();

        Log log = Log.builder()
                .lastOccurrence(LocalDateTime.now())
                .origin("168.122.0.2")
                .description("Error description")
                .title("Error title")
                .user(user)
                .build();

        Occurrence occurrence = Occurrence.builder()
                .environment(Environment.DEVELOPMENT)
                .level(Level.DEBUG)
                .log(log)
                .status(null)
                .timestamp(LocalDateTime.now())
                .build();

        Set<ConstraintViolation<Occurrence>> constraintViolations = validator
                .validate(occurrence);

        assertEquals(1, constraintViolations.size());
    }
}
