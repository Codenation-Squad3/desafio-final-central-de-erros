package br.com.codenation.desafio.config;

import br.com.codenation.desafio.login.LoggedUser;
import br.com.codenation.desafio.model.User;
import br.com.codenation.desafio.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Getter
@Configuration
@EnableAuthorizationServer
@ConfigurationProperties(prefix = "authorization")
@PropertySource("classpath:application.yml")
public class AuthenticationServerConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${email}")
    private String OAUTH_USER_EMAIL;
    @Value("${password}")
    private String OAUTH_USER_PASSWORD;
    private String OAUTH_USER_NAME = "Admin";

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManagerBean();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth, UserRepository userRepository) throws Exception {
        if (userRepository.count() == 0) {
            userRepository.save(User.builder()
                    .email(OAUTH_USER_EMAIL)
                    .password(passwordEncoder().encode(OAUTH_USER_PASSWORD))
                    .nome(OAUTH_USER_NAME)
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
