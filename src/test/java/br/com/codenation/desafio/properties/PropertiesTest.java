package br.com.codenation.desafio.properties;


import br.com.codenation.desafio.config.AuthenticationServerConfiguration;
import br.com.codenation.desafio.config.AuthorizationServerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {

    @Autowired
    private AuthenticationServerConfiguration authenticationServerConfiguration;
    @Autowired
    private AuthorizationServerConfig authorizationServerConfig;

    @Test
    public void propertiesValue_adminUser_shouldBeConfigured() {
        assertThat(authenticationServerConfiguration.getOAUTH_USER_EMAIL(), is("admin@admin.com"));
        assertThat(authenticationServerConfiguration.getOAUTH_USER_NAME(), is("Admin"));
        assertThat(authenticationServerConfiguration.getOAUTH_USER_PASSWORD(), is("admin"));
    }

    @Test
    public void propertiesValue_oauthClient_shouldBeConfigured() {
        assertThat(authorizationServerConfig.getOAUTH_CLIENT_ID(), is("client-id"));
        assertThat(authorizationServerConfig.getOAUTH_CLIENT_SECRET(), is("client-secret"));
        assertThat(authorizationServerConfig.getOAUTH_CLIENT_GRANT_TYPES(), arrayContaining("password",
                "authorization_code", "refresh_token", "implicit"));
        assertThat(authorizationServerConfig.getOAUTH_CLIENT_SCOPES(), arrayContaining("read",
                "write", "trust"));
    }
}
