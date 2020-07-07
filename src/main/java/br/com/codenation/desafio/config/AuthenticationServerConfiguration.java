package br.com.codenation.desafio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import br.com.codenation.desafio.login.LoggedUser;
import br.com.codenation.desafio.model.User;
import br.com.codenation.desafio.repository.UserRepository;

@Configuration
@EnableAuthorizationServer
public class AuthenticationServerConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${codenation.oauth.user.email}")
	private String email;
	
	@Value("${codenation.oauth.user.password}")
	private String password;
	
	@Value("${codenation.oauth.user.name}")
	private String name;
	
	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManagerBean();
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth, UserRepository userRepository) throws Exception {

		String e = "${codenation.oauth.user.name}";
		if (userRepository.count() == 0) {
			userRepository.save(User.builder()
					.email(email)
					.password(passwordEncoder().encode(password))
					.nome(name)
					.build());
		}
		auth.userDetailsService(email -> userRepository.findByEmail(email)
				.map(LoggedUser::new)
				.orElse(null)).passwordEncoder(passwordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
