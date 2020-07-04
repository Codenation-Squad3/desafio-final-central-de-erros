package br.com.codenation.desafio.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties(prefix = "codenation.oauth.client")
public class OAuthClient {

    //TODO - mover para properties
    public static final String clientId = "client-id";
    public static final String clientSecret = "client-secret";
    public static final String[] grantTypes = {"password", "authorization_code", "refresh_token", "implicit"};
    public static final String[] scopes = {"read", "write", "trust"};
}
