package br.com.codenation.desafio.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class OAuthClient {

    public static final String clientId = "client-id";
    public static final String clientSecret = "client-secret";
    public static final String[] grantTypes = {"password", "authorization_code", "refresh_token", "implicit"};
    public static final String[] scopes = {"read", "write", "trust"};
}
