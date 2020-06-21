package br.com.codenation.desafio.config;

import br.com.codenation.desafio.constants.OAuthUser;
import br.com.codenation.desafio.login.LoggedUser;
import br.com.codenation.desafio.model.User;
import br.com.codenation.desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class AuthenticationServerConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManagerBean();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth, UserRepository userRepository) throws Exception {
        if (userRepository.count() == 0) {
            userRepository.save(User.builder()
                    .email(OAuthUser.email)
                    .password(passwordEncoder().encode(OAuthUser.password))
                    .nome(OAuthUser.name)
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
