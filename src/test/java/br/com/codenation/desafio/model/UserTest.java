package br.com.codenation.desafio.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class UserTest {
	
	private static Validator validator;
	
	@BeforeAll
	public static void setUp() {
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    validator = factory.getValidator();
	}
	
	@Test
	public void userEmailNomePasswordIsNull() {
		User user = User.builder()
				.build();
		
        Set<ConstraintViolation<User>> constraintViolations = validator
        		.validate(user);
        
        assertEquals(3, constraintViolations.size());
	}
	
	@Test
	public void userEmailIsNotValid() {
		User user = User.builder()
				.email("aabbbcc")
				.nome("John Doe")
				.password("123456")
				.build();
		
        Set<ConstraintViolation<User>> constraintViolations = validator
        		.validate(user);
        
        assertEquals(1, constraintViolations.size());
	}
	
	@Test
	public void userEmailIsValid() {
		User user = User.builder()
				.email("john.doe@gmail.com")
				.nome("John Doe")
				.password("123456")
				.build();
		
        Set<ConstraintViolation<User>> constraintViolations = validator
        		.validate(user);
        
        assertEquals(0, constraintViolations.size());
	}
	
	@Test
	public void userNomeIsNotValid() {
		User user = User.builder()
				.email("john.doe@gmail.com")
				.nome("Jo")
				.password("123456")
				.build();
		
        Set<ConstraintViolation<User>> constraintViolations = validator
        		.validate(user);
        
        assertEquals(1, constraintViolations.size());
        
        constraintViolations.forEach(
        		cv -> System.out.println(cv.getPropertyPath() + " " + cv.getMessage())
        		);
	}
	
	@Test
	public void userPasswordIsNotValid() {
		User user = User.builder()
				.email("john.doe@gmail.com")
				.nome("John Doe")
				.password("12345")
				.build();
		
        Set<ConstraintViolation<User>> constraintViolations = validator
        		.validate(user);
        
        assertEquals(1, constraintViolations.size());
        
        constraintViolations.forEach(
        		cv -> System.out.println(cv.getPropertyPath() + " " + cv.getMessage())
        		);
	}
	
	@Test
	public void userIsValid() {
		User user = User.builder()
				.email("john.doe@gmail.com")
				.nome("John Doe")
				.password("123456")
				.build();
		
        Set<ConstraintViolation<User>> constraintViolations = validator
        		.validate(user);
        
        assertEquals(0, constraintViolations.size());
        /*
        constraintViolations.forEach(
        		cv -> System.out.println(cv.getPropertyPath() + " " + cv.getMessage())
        		);
        		*/
	}
}
