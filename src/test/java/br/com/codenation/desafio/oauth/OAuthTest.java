package br.com.codenation.desafio.oauth;

import br.com.codenation.desafio.config.AuthenticationServerConfiguration;
import br.com.codenation.desafio.model.User;
import br.com.codenation.desafio.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Base64;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OAuthTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationServerConfiguration authenticationServerConfiguration;
    private final String userEmail = "oauth@teste.com";
    private final String userPassword = "password";
    public static final String CLIENT_ID = "client-id";
    public static final String CLIENT_SECRET = "client-secret";
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    private User createUser() {
        User user = User.builder()
                .nome("Teste")
                .email(userEmail)
                .password(authenticationServerConfiguration.passwordEncoder().encode(userPassword))
                .createdAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    @Test
    @Transactional
    public void givenAuthRequestOnPrivateWithTokenShouldResponseOk() throws Exception {
        createUser();
        mockMvc.perform(MockMvcRequestBuilders.get("/oauthtest")
                .header("Authorization", "Bearer " + obtainAccessToken())
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Somente mostrado se usuário estiver logado.")));
    }

    @Test
    public void givenAuthRequestOnPrivateWithoutTokenShouldResponseUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/oauthtest")
                .accept(MediaType.ALL))
                .andExpect(status().isUnauthorized());
    }

    private String obtainAccessToken() throws Exception {
        String encodedCredentials = Base64.getEncoder().encodeToString(
                (CLIENT_ID + ":" + CLIENT_SECRET).getBytes());
        String authorizationHeader = "Basic " + encodedCredentials;

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/oauth/token?username="+ userEmail +
                        "&password=" + userPassword + "&grant_type=password")
                .header("Authorization", authorizationHeader)
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
}
