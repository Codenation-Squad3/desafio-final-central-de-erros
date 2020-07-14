package br.com.codenation.desafio.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import br.com.codenation.desafio.enums.Status;


@SpringBootTest
public class LogTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void logIsNotValid() {
        Log log = Log.builder()
                .build();

        Set<ConstraintViolation<Log>> constraintViolations = validator
                .validate(log);

        assertEquals(8, constraintViolations.size());
    }

    @Test
    public void logTitleIsNotValid() {
        User user = User.builder()
                .email("jonh.doe@gmail.com")
                .nome("Jonh Doe")
                .password("123456")
                .build();

        Log log = Log.builder()
                .title("s")
                .lastOccurrence(LocalDateTime.now())
                .origin("service api 127.0.0.1:8080")
                .description("NullPointer Exception")
                .title("t")
                .description("description description")
                .lastOccurrence(LocalDateTime.now())
                .user(user)
                .status(Status.ACTIVE)
                .level(Level.DEBUG)
                .environment(Environment.DEVELOPMENT)
                .build();

        Set<ConstraintViolation<Log>> constraintViolations = validator
                .validate(log);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void logLogIsNotValid() {
        User user = User.builder()
                .email("jonh.doe@gmail.com")
                .nome("Jonh Doe")
                .password("123456")
                .build();

        Log log = Log.builder()
                .title("some error in test environment")
                .description("s")
                .lastOccurrence(LocalDateTime.now())
                .user(user)
                .status(Status.ACTIVE)
                .level(Level.DEBUG)
                .environment(Environment.DEVELOPMENT)
                .build();

        Set<ConstraintViolation<Log>> constraintViolations = validator
                .validate(log);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void logDatetimeIsNotValid() {
        User user = User.builder()
                .email("jonh.doe@gmail.com")
                .nome("Jonh Doe")
                .password("123456")
                .build();

        Log log = Log.builder()
                .title("some error in test environment")
                .description("description of the error stack trace")
                .lastOccurrence(LocalDateTime.now())
                .user(user)
                .status(Status.ACTIVE)
                .level(Level.DEBUG)
                .environment(Environment.DEVELOPMENT)
                .build();

        Set<ConstraintViolation<Log>> constraintViolations = validator
                .validate(log);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void logDescriptionIsNotValid() {
        
        User user = User.builder()
                .email("jonh.doe@gmail.com")
                .nome("Jonh Doe")
                .password("123456")
                .build();

        Log log = Log.builder()
                .title("some error in test environment")
                .lastOccurrence(LocalDateTime.now())
                .description(null)
                .origin("128.168.0.3")
                .status(Status.ACTIVE)
                .level(Level.DEBUG)
                .environment(Environment.DEVELOPMENT)
                .user(user)
                .build();

        Set<ConstraintViolation<Log>> constraintViolations = validator
                .validate(log);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void logUserIsNotValid() {    
        
        Log log = Log.builder()
                .title("some error in test environment")
                .description("description of the error stack trace")
                .lastOccurrence(LocalDateTime.now())
                .origin("128.168.0.3")
                .status(Status.ACTIVE)
                .level(Level.DEBUG)
                .environment(Environment.DEVELOPMENT)
                .user(null)
                .build();

        Set<ConstraintViolation<Log>> constraintViolations = validator
                .validate(log);

        constraintViolations.forEach(
                cv -> System.out.println(cv.getPropertyPath() + " " + cv.getMessage()));
        
        assertEquals(1, constraintViolations.size());
    }
}
