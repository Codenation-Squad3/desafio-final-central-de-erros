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


public class LogsTest {
	
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

        assertEquals(5, constraintViolations.size());
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
				.user(user)
				.build();
		
        Set<ConstraintViolation<Log>> constraintViolations = validator
        		.validate(log);
        
        assertEquals(1, constraintViolations.size());
	}

	@Test
	public void logOriginIsNotValid() {
		User user = User.builder()
				.email("jonh.doe@gmail.com")
				.nome("Jonh Doe")
				.password("123456")
				.build();

		Log log = Log.builder()
				.title("some error in test environment")
				.lastOccurrence(LocalDateTime.now())
				.description("some description error")
				.origin(null)
				.user(user)
				.build();

		Set<ConstraintViolation<Log>> constraintViolations = validator
				.validate(log);

		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void logLastOccurrenceIsNotValid() {
		User user = User.builder()
				.email("jonh.doe@gmail.com")
				.nome("Jonh Doe")
				.password("123456")
				.build();
				
		Log log = Log.builder()
				.title("some error in test environment")
				.lastOccurrence(null)
				.description("some description error")
				.origin("128.168.0.3")
				.user(user)
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
				.user(user)
				.build();
		
        Set<ConstraintViolation<Log>> constraintViolations = validator
        		.validate(log);
        
        assertEquals(1, constraintViolations.size());
	}
	
	@Test
	public void logLUserIsNotValid() {
		Log log = Log.builder()
				.title("some error in test environment")
				.lastOccurrence(LocalDateTime.now())
				.description("some description error")
				.origin("128.168.0.3")
				.user(null)
				.build();
		
        Set<ConstraintViolation<Log>> constraintViolations = validator
        		.validate(log);
        
        assertEquals(1, constraintViolations.size());

	}

}
