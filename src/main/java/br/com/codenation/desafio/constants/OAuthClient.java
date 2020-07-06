package br.com.codenation.desafio.constants;

public class OAuthClient {

    public static final String clientId = "${codenation.oauth.client.client-id}";
    public static final String clientSecret = "${codenation.oauth.client.client-secret}";
    public static final String[] grantTypes = {"password", "authorization_code", "refresh_token", "implicit"};
    public static final String[] scopes = {"read", "write", "trust"};
}
