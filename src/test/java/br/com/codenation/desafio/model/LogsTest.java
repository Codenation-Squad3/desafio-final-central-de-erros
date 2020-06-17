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

import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;

public class LogsTest {
	
	private static Validator validator;
	
	@BeforeAll
	public static void setUp() {
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    validator = factory.getValidator();
	}
	
	@Test
	public void logIsNotValid() {
		Logs log = Logs.builder()
				.build();
		
        Set<ConstraintViolation<Logs>> constraintViolations = validator
        		.validate(log);
        
        assertEquals(7, constraintViolations.size());
	}
	
	@Test
	public void logTitleIsNotValid() {
		User user = User.builder()
				.email("jonh.doe@gmail.com")
				.nome("Jonh Doe")
				.password("123456")
				.build();
				
		Logs log = Logs.builder()
				.title("s")
				.log("some error in line 123456")
				.datetime(LocalDateTime.now())
				.environment(Environment.TEST)
				.source("service api 127.0.0.1:8080")
				.level(Level.DEBUG)
				.user(user)
				.build();
		
        Set<ConstraintViolation<Logs>> constraintViolations = validator
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
				
		Logs log = Logs.builder()
				.title("some error in test environment")
				.log("s")
				.datetime(LocalDateTime.now())
				.environment(Environment.TEST)
				.source("service api 127.0.0.1:8080")
				.level(Level.DEBUG)
				.user(user)
				.build();
		
        Set<ConstraintViolation<Logs>> constraintViolations = validator
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
				
		Logs log = Logs.builder()
				.title("some error in test environment")
				.log("description of the error stack trace")
				.datetime(null)
				.environment(Environment.TEST)
				.source("service api 127.0.0.1:8080")
				.level(Level.DEBUG)
				.user(user)
				.build();
		
        Set<ConstraintViolation<Logs>> constraintViolations = validator
        		.validate(log);
        
        assertEquals(1, constraintViolations.size());
	}
	
	@Test
	public void logEnvironmentIsNotValid() {
		User user = User.builder()
				.email("jonh.doe@gmail.com")
				.nome("Jonh Doe")
				.password("123456")
				.build();
				
		Logs log = Logs.builder()
				.title("some error in test environment")
				.log("description of the error stack trace")
				.datetime(LocalDateTime.now())
				.environment(null)
				.source("service api 127.0.0.1:8080")
				.level(Level.DEBUG)
				.user(user)
				.build();
		
        Set<ConstraintViolation<Logs>> constraintViolations = validator
        		.validate(log);
        
        assertEquals(1, constraintViolations.size());
	}
	
	@Test
	public void logSourceIsNotValid() {
		User user = User.builder()
				.email("jonh.doe@gmail.com")
				.nome("Jonh Doe")
				.password("123456")
				.build();
				
		Logs log = Logs.builder()
				.title("some error in test environment")
				.log("description of the error stack trace")
				.datetime(LocalDateTime.now())
				.environment(Environment.TEST)
				.source("s")
				.level(Level.DEBUG)
				.user(user)
				.build();
		
        Set<ConstraintViolation<Logs>> constraintViolations = validator
        		.validate(log);
        
        assertEquals(1, constraintViolations.size());
	}
	
	@Test
	public void logLevelIsNotValid() {
		User user = User.builder()
				.email("jonh.doe@gmail.com")
				.nome("Jonh Doe")
				.password("123456")
				.build();
				
		Logs log = Logs.builder()
				.title("some error in test environment")
				.log("description of the error stack trace")
				.datetime(LocalDateTime.now())
				.environment(Environment.TEST)
				.source("service api 127.0.0.1:8080")
				.level(null)
				.user(user)
				.build();
		
        Set<ConstraintViolation<Logs>> constraintViolations = validator
        		.validate(log);
        
        assertEquals(1, constraintViolations.size());
	}
	
	@Test
	public void logLUserIsNotValid() {				
		Logs log = Logs.builder()
				.title("some error in test environment")
				.log("description of the error stack trace")
				.datetime(LocalDateTime.now())
				.environment(Environment.TEST)
				.source("service api 127.0.0.1:8080")
				.level(Level.DEBUG)
				.user(null)
				.build();
		
        Set<ConstraintViolation<Logs>> constraintViolations = validator
        		.validate(log);
        
        assertEquals(1, constraintViolations.size());
        /*
        constraintViolations.forEach(
        		cv -> System.out.println(cv.getPropertyPath() + " " + cv.getMessage())
        		);*/
	}

}
