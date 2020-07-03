package br.com.codenation.desafio.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import br.com.codenation.desafio.enums.Status;
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
		User user = createUser();
		Log log = createLog(user);
		Ocurrence Ocurrence = createOcurrence(log, user);
		
		Optional<User> myUser = userRepository.findById(user.getId());
		Optional<Ocurrence> myOcurrence = OcurrenceRepository.findById(Ocurrence.getId());
		
		assertThat(myUser.isPresent()).isTrue();
		assertThat(myOcurrence.isPresent()).isTrue();
	}
	
	
	// more ...
	
	
	
	private Log createLog(User user) {
		Log log = Log.builder()
				.title("error 8573 user interaction")
				.description("some error with stack trace line 1234")
				.origin("in 127.0.0.1 some app")
				.lastOccurrence(LocalDateTime.now())
				.user(user)
				.build();
		return logRepository.save(log);
	}
	
	private User createUser() {
		User user = User.builder()
				.nome("John Doe")
				.email("john.doe@gmail.com")
				.password("123456")
				.createdAt(LocalDateTime.now())
				.build();
		return userRepository.save(user);
	}
	
	private Ocurrence createOcurrence(Log log, User user) {
		
		Ocurrence ocurrence = Ocurrence.builder()
				.dtCreated(LocalDateTime.now())
				.user(user)
				.log(log)
				.build();
		return OcurrenceRepository.save(ocurrence);
	}
	
}
