package br.com.codenation.desafio.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import br.com.codenation.desafio.enums.Status;
import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.model.User;
import br.com.codenation.desafio.repository.UserRepository;
import br.com.codenation.desafio.request.LogRequest;


@SpringBootTest
public class LogServiceTest {

	private static final String TITLE = "log title 1";
	private static final String DESCRIPTION = "stack trace log";
	private static final String ORIGIN = "api server xyz";
	private static final String EMAIL = "admin@admin.com";
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	@Transactional
	public void whenSaveLog_itShouldReturnLog() {
		User user = getUser();
		
		LogRequest logRequest = LogRequest.builder()
				.title(TITLE)
				.description(DESCRIPTION)
				.origin(ORIGIN)
				.environment(Environment.DEVELOPMENT)
				.lastOccurrence(LocalDateTime.now())
				.status(Status.ACTIVE)
				.level(Level.DEBUG)
				.userId(user.getId())
				.build();
		
		Log log = logService.save(logRequest);

		assertThat(log).isNotNull();
		assertThat(logService.findAll().getContent().size()).isEqualTo(1);
	}
	
	@Test
	@Transactional
	public void whenFindAll_mustReturnCorrectCount() {
		User user = getUser();
		LogRequest logRequest1 = LogRequest.builder()
				.title("error test 1")
				.description(DESCRIPTION)
				.origin(ORIGIN)
				.environment(Environment.DEVELOPMENT)
				.lastOccurrence(LocalDateTime.now())
				.status(Status.ACTIVE)
				.level(Level.DEBUG)
				.userId(user.getId())
				.build();
		LogRequest logRequest2 = LogRequest.builder()
				.title("error test 2")
				.description(DESCRIPTION)
				.origin(ORIGIN)
				.environment(Environment.DEVELOPMENT)
				.lastOccurrence(LocalDateTime.now())
				.status(Status.ACTIVE)
				.level(Level.DEBUG)
				.userId(user.getId())
				.build();
		
		logService.save(logRequest1);
		logService.save(logRequest2);

		assertThat(logService.findAll().getTotalElements()).isEqualTo(2l);
	}

	@Test
	@Transactional
	public void whenFindByExampleWithStatusAndEnvironmentAndLevel_mustReturnCorrectCount() {
		User user = getUser();
		LogRequest logRequest1 = LogRequest.builder()
				.title("error test 1")
				.description(DESCRIPTION)
				.origin(ORIGIN)
				.environment(Environment.DEVELOPMENT)
				.lastOccurrence(LocalDateTime.now())
				.status(Status.ACTIVE)
				.level(Level.DEBUG)
				.userId(user.getId())
				.build();
		LogRequest logRequest2 = LogRequest.builder()
				.title("error test 1")
				.description(DESCRIPTION)
				.origin(ORIGIN)
				.environment(Environment.PRODUCTION)
				.lastOccurrence(LocalDateTime.now())
				.status(Status.ACTIVE)
				.level(Level.DEBUG)
				.userId(user.getId())
				.build();
		Log log = Log.builder()
				.environment(Environment.DEVELOPMENT)
				.status(Status.ACTIVE)
				.level(Level.DEBUG)
				.build();
		
		logService.save(logRequest1);
		logService.save(logRequest2);
		
		assertThat(logService.findByExample(log, 0, "level", "ASC").getTotalElements()).isEqualTo(1l);
	}

	@Test
	@Transactional
	public void whenFindByExampleWithStatus_mustReturnCorrectCount() {
		User user = getUser();
		LogRequest logRequest1 = LogRequest.builder()
				.title("error test 1")
				.description(DESCRIPTION)
				.origin(ORIGIN)
				.environment(Environment.DEVELOPMENT)
				.lastOccurrence(LocalDateTime.now())
				.status(Status.ACTIVE)
				.level(Level.DEBUG)
				.userId(user.getId())
				.build();
		LogRequest logRequest2 = LogRequest.builder()
				.title("error test 1")
				.description(DESCRIPTION)
				.origin(ORIGIN)
				.environment(Environment.DEVELOPMENT)
				.lastOccurrence(LocalDateTime.now())
				.status(Status.EXCLUDED)
				.level(Level.DEBUG)
				.userId(user.getId())
				.build();
		Log log = Log.builder()
				.status(Status.ACTIVE)
				.build();
		
		logService.save(logRequest1);
		logService.save(logRequest2);
		
		assertThat(logService.findByExample(log, 0, "level", "ASC").getTotalElements()).isEqualTo(1l);
	}

	@Test
	@Transactional
	public void whenFindByExampleWithEnvironment_mustReturnCorrectCount() {
		User user = getUser();
		LogRequest logRequest1 = LogRequest.builder()
				.title("error test 1")
				.description(DESCRIPTION)
				.origin(ORIGIN)
				.environment(Environment.PRODUCTION)
				.lastOccurrence(LocalDateTime.now())
				.status(Status.ACTIVE)
				.level(Level.DEBUG)
				.userId(user.getId())
				.build();
		LogRequest logRequest2 = LogRequest.builder()
				.title("error test 2")
				.description(DESCRIPTION)
				.origin(ORIGIN)
				.environment(Environment.DEVELOPMENT)
				.lastOccurrence(LocalDateTime.now())
				.status(Status.EXCLUDED)
				.level(Level.DEBUG)
				.userId(user.getId())
				.build();
		Log log = Log.builder()
				.environment(Environment.DEVELOPMENT)
				.build();
		
		logService.save(logRequest1);
		logService.save(logRequest2);
		
		assertThat(logService.findByExample(log, 0, "level", "ASC").getTotalElements()).isEqualTo(1l);
	}

	@Test
	@Transactional
	public void whenFindByExampleWithLevel_mustReturnCorrectCount() {
		User user = getUser();
		LogRequest logRequest1 = LogRequest.builder()
				.title("error test 1")
				.description(DESCRIPTION)
				.origin(ORIGIN)
				.environment(Environment.PRODUCTION)
				.lastOccurrence(LocalDateTime.now())
				.status(Status.ACTIVE)
				.level(Level.DEBUG)
				.userId(user.getId())
				.build();
		LogRequest logRequest2 = LogRequest.builder()
				.title("error test 2")
				.description(DESCRIPTION)
				.origin(ORIGIN)
				.environment(Environment.DEVELOPMENT)
				.lastOccurrence(LocalDateTime.now())
				.status(Status.ACTIVE)
				.level(Level.ERROR)
				.userId(user.getId())
				.build();
		Log log = Log.builder()
				.environment(Environment.DEVELOPMENT)
				.build();
		
		logService.save(logRequest1);
		logService.save(logRequest2);
		
		assertThat(logService.findByExample(log, 0, "level", "ASC").getTotalElements()).isEqualTo(1l);
	}

	@Test
	@Transactional
	public void whenFindByExampleWithTitle_mustReturnCorrectCount() {
		User user = getUser();
		LogRequest logRequest1 = LogRequest.builder()
				.title(TITLE)
				.description(DESCRIPTION)
				.origin(ORIGIN)
				.environment(Environment.PRODUCTION)
				.lastOccurrence(LocalDateTime.now())
				.status(Status.ACTIVE)
				.level(Level.DEBUG)
				.userId(user.getId())
				.build();
		LogRequest logRequest2 = LogRequest.builder()
				.title(TITLE+"2")
				.description(DESCRIPTION)
				.origin(ORIGIN)
				.environment(Environment.DEVELOPMENT)
				.lastOccurrence(LocalDateTime.now())
				.status(Status.ACTIVE)
				.level(Level.DEBUG)
				.userId(user.getId())
				.build();
		Log log = Log.builder()
				.title(TITLE)
				.build();
		
		logService.save(logRequest1);
		logService.save(logRequest2);
		
		assertThat(logService.findByExample(log, 0, "level", "ASC").getTotalElements()).isEqualTo(1l);
	}

	private User getUser() {
		return userRepository.findByEmail(EMAIL).get();
	}
}