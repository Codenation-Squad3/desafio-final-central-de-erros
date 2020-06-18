package br.com.codenation.desafio.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.model.Ocurrence;
import br.com.codenation.desafio.model.User;

@SpringBootTest
public class LogRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LogRepository logRepository;
	
	@Autowired
	private OcurrenceRepository OcurrenceRepository;
	
	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(userRepository).isNotNull();
		assertThat(logRepository).isNotNull();
		assertThat(OcurrenceRepository).isNotNull();
	}
	
	@Test
	public void whenCreateLog_thenFindUserAndOcurrenceAreNotNull() {
		Log log = createLog();
		User user = createUser();
		Ocurrence Ocurrence = createOcurrence(log, user);
		
		Optional<User> myUser = userRepository.findById(user.getId());
		Optional<Ocurrence> myOcurrence = OcurrenceRepository.findById(Ocurrence.getId());
		
		assertThat(myUser.isPresent()).isTrue();
		assertThat(myOcurrence.isPresent()).isTrue();
	}
	
	
	
	
	
	
	private Log createLog() {
		Log log = Log.builder()
				.description("some error with stack trace line 1234")
				.origin("in 127.0.0.1 some app")
				.lastOcurrence(LocalDateTime.now())
				.build();
		return logRepository.save(log);
	}
	
	private User createUser() {
		User user = User.builder()
				.nome("John Doe")
				.email("john.doe@gmail.com")
				.password("123456")
				.build();
		return userRepository.save(user);
	}
	
	private Ocurrence createOcurrence(Log log, User user) {
		Ocurrence Ocurrence = Ocurrence.builder()
				.environment(Environment.TEST)
				.level(Level.DEBUG)
				.dtCreated(LocalDateTime.now())
				.user(user)
				.log(log)
				.build();
		return OcurrenceRepository.save(Ocurrence);
	}
	
}
