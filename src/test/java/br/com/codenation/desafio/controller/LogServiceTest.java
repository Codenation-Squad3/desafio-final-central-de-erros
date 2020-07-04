package br.com.codenation.desafio.controller;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import br.com.codenation.desafio.enums.Status;
import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.model.User;
import br.com.codenation.desafio.repository.UserRepository;
import br.com.codenation.desafio.request.LogRequest;
import br.com.codenation.desafio.service.LogService;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
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
		
		LogRequest logRequest = createLogRequest(TITLE, DESCRIPTION, ORIGIN,
				Environment.DEVELOPMENT, LocalDateTime.now(), Status.ACTIVE,
				Level.DEBUG, user.getId());
		Log log = logService.save(logRequest);

		assertThat(log).isNotNull();
		assertThat(logService.findAll().getContent().size()).isEqualTo(1);
	}
	
	@Test
	@Transactional
	public void whenFindAll_mustReturnCorrectCount() {
		initLogs();
		assertThat(logService.findAll().getTotalElements()).isEqualTo(3l);
	}
	
	public void whenFindByExampleWithStatusAndEnvironmentAndLevel_mustReturnCorrectCount() {
		initLogs();
		Log log = Log.builder()
				.environment(Environment.DEVELOPMENT)
				.status(Status.ACTIVE)
				.level(Level.DEBUG)
				.build();
		assertThat(logService.findByExample(log,0,"level","ASC").getTotalElements()).isEqualTo(1l);
	}
	
	public void whenFindByExampleWithStatus_mustReturnCorrectCount() {
		initLogs();
		Log log = Log.builder()
				.status(Status.ACTIVE)
				.build();
		assertThat(logService.findByExample(log,0,"status","ASC").getTotalElements()).isEqualTo(1l);
	}
	
	public void whenFindByExampleWithEnvironment_mustReturnCorrectCount() {
		initLogs();
		Log log = Log.builder()
				.environment(Environment.DEVELOPMENT)
				.build();
		assertThat(logService.findByExample(log,0,"environment","ASC").getTotalElements()).isEqualTo(1l);
	}
	
	public void whenFindByExampleWithLevel_mustReturnCorrectCount() {
		initLogs();
		Log log = Log.builder()
				.level(Level.DEBUG)
				.build();
		assertThat(logService.findByExample(log,0,"level","ASC").getTotalElements()).isEqualTo(1l);
	}
	
	public void whenFindByExampleWithTitle_mustReturnCorrectCount() {
		initLogs();
		Log log = Log.builder()
				.title(TITLE+"1")
				.build();
		assertThat(logService.findByExample(log,0,"title","ASC").getTotalElements()).isEqualTo(1l);
	}
	
	public void whenFindByExampleWithDescription_mustReturnCorrectCount() {
		initLogs();
		Log log = Log.builder()
				.description(DESCRIPTION)
				.build();
		assertThat(logService.findByExample(log,0,"description","ASC").getTotalElements()).isEqualTo(1l);
	}
	
	public void whenFindByExampleWithOrigin_mustReturnCorrectCount() {
		initLogs();
		Log log = Log.builder()
				.origin(ORIGIN)
				.build();
		assertThat(logService.findByExample(log,0,"origin","ASC").getTotalElements()).isEqualTo(1l);
	}
	
	
	private User getUser() {
		return userRepository.findByEmail(EMAIL).get();
	}
	
	private void initLogs() {
		User user = getUser();
		
		logService.save(
			createLogRequest(TITLE+"1", DESCRIPTION, ORIGIN, Environment.DEVELOPMENT,
				LocalDateTime.now(), Status.ACTIVE, Level.DEBUG, user.getId()));
		logService.save(
			createLogRequest(TITLE+"2", DESCRIPTION, ORIGIN, Environment.PRODUCTION,
				LocalDateTime.now(), Status.ARCHIVED, Level.ERROR, user.getId()));
		logService.save(
			createLogRequest(TITLE+"3", DESCRIPTION, ORIGIN, Environment.TEST,
				LocalDateTime.now(), Status.EXCLUDED, Level.WARNING, user.getId()));
	}
	
	private LogRequest createLogRequest(String title, String description,
			String origin, Environment environment, LocalDateTime lastOcurrence,
			Status status, Level level, UUID userId) {
		return LogRequest.builder()
			.title(title)
			.description(description)
			.origin(origin)
			.environment(environment)
			.lastOccurrence(lastOcurrence)
			.status(status)
			.level(level)
			.userId(userId)
			.build();
	}
	
	private Log createLog(String title, String description,
			String origin, Environment environment, LocalDateTime lastOcurrence,
			Status status, Level level) {
		return Log.builder()
				.title(title)
				.description(description)
				.origin(origin)
				.environment(environment)
				.lastOccurrence(lastOcurrence)
				.status(status)
				.level(level)
				.build();
	}
}
