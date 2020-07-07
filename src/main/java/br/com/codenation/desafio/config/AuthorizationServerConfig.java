package br.com.codenation.desafio.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
@ConfigurationProperties(prefix = "authorization.client")
@PropertySource("classpath:application.yml")
@Getter
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter {

    @Value("${id}")
    private String OAUTH_CLIENT_ID;
    @Value("${secret}")
    private String OAUTH_CLIENT_SECRET;
    @Value("${grant-types}")
    private String[] OAUTH_CLIENT_GRANT_TYPES;
    @Value("${scopes}")
    private String[] OAUTH_CLIENT_SCOPES;
    private static final int ACCESS_TOKEN_VALIDITY_IN_SECONDS = 60 * 20;
    private static final int REFRESH_TOKEN_VALIDITY_IN_SECONDS = 60 * 60;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(OAUTH_CLIENT_ID)
                .secret(new BCryptPasswordEncoder().encode(OAUTH_CLIENT_SECRET))
                .authorizedGrantTypes(OAUTH_CLIENT_GRANT_TYPES)
                .scopes(OAUTH_CLIENT_SCOPES)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_IN_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_IN_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }
}
