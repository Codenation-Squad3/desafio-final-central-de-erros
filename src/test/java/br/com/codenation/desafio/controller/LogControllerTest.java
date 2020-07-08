package br.com.codenation.desafio.controller;

import br.com.codenation.desafio.config.AuthenticationServerConfiguration;
import br.com.codenation.desafio.constants.PatchMediaType;
import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import br.com.codenation.desafio.enums.Status;
import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.model.User;
import br.com.codenation.desafio.repository.LogRepository;
import br.com.codenation.desafio.request.LogRequest;
import com.google.gson.Gson;
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
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LogControllerTest {

    public static final String LOG_TITLE = "Log Title 1";
    public static final String LOG_TITLE_2 = "Log Title 2";
    public static final String LOG_DESCRIPTION = "Description 1";
    public static final String LOG_ORIGIN = "Api test";
    public static final Environment LOG_ENVIRONMENT = Environment.PRODUCTION;
    public static final Status LOG_STATUS = Status.ACTIVE;
    public static final Level LOG_LEVEL = Level.ERROR;
    public static final String USER_EMAIL = "teste@test.com";
    public static final String USER_NAME = "teste";
    public static final String USER_PASSWORD = "senha";
    public static final String USER_TOKEN = "1234";
    public static final String CLIENT_ID = "client-id";
    public static final String CLIENT_SECRET = "client-secret";

    private Log log;
    private MockMvc mockMvc;
    private User user;
    private String accessToken;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private AuthenticationServerConfiguration authenticationServerConfiguration;


    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        this.user = User.builder()
                .email(USER_EMAIL)
                .nome(USER_NAME)
                .password(authenticationServerConfiguration.passwordEncoder().encode(USER_PASSWORD))
                .createdAt(LocalDateTime.now())
                .token(USER_TOKEN)
                .build();

        this.log = logRepository.save(Log.builder()
                .title(LOG_TITLE)
                .description(LOG_DESCRIPTION)
                .origin(LOG_ORIGIN)
                .environment(LOG_ENVIRONMENT)
                .lastOccurrence(LocalDateTime.now())
                .status(LOG_STATUS)
                .level(LOG_LEVEL)
                .user(user)
                .build());

        this.accessToken = this.obtainAccessToken();
    }

    @Test
    @Transactional
    public void updateLog_changeStatus_ShouldUpdateStatusOfLog() throws Exception {
        String newStatusOfLog = Status.EXCLUDED.toString();
        String jsonBody = "'{'\"status\": \"{0}\"'}'";
        String jsonBodyFormated = MessageFormat.format(jsonBody, newStatusOfLog);

        mockMvc.perform(MockMvcRequestBuilders.patch("/logs/" + this.log.getId())
        		.header("Authorization", "Bearer " + this.accessToken)
                .contentType(PatchMediaType.APPLICATION_MERGE_PATCH)
                .content(jsonBodyFormated))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(LOG_TITLE)))
                .andExpect(jsonPath("$.description", is(LOG_DESCRIPTION)))
                .andExpect(jsonPath("$.origin", is(LOG_ORIGIN)))
                .andExpect(jsonPath("$.environment", is(LOG_ENVIRONMENT.toString())))
                .andExpect(jsonPath("$.level", is(LOG_LEVEL.toString())))
                .andExpect(jsonPath("$.status", is(newStatusOfLog)));
    }

    @Test
    @Transactional
    public void saveLog_CorrectInput_ShouldSaveAndResponseCreated() throws Exception {
        LogRequest logRequest = LogRequest.builder()
                .title(LOG_TITLE_2)
                .description(LOG_DESCRIPTION)
                .origin(LOG_ORIGIN)
                .environment(Environment.DEVELOPMENT)
                .status(Status.ACTIVE)
                .level(Level.WARNING)
                .userId(user.getId())
                .build();
        String requestBody = new Gson().toJson(logRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/logs/")
                .header("Authorization", "Bearer " + this.accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated());

        assertThat(logRepository.count(), is(2L));
    }

    @Test
    @Transactional
    public void saveLog_IncorrectUserIdAtRequest_ShouldResponseNotFound() throws Exception {
        LogRequest logRequest = LogRequest.builder()
                .title(LOG_TITLE_2)
                .description(LOG_DESCRIPTION)
                .origin(LOG_ORIGIN)
                .environment(Environment.DEVELOPMENT)
                .status(Status.ACTIVE)
                .level(Level.WARNING)
                .userId(UUID.randomUUID())
                .build();
        String requestBody = new Gson().toJson(logRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/logs/")
                .header("Authorization", "Bearer " + this.accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("User not found")));

        assertThat(logRepository.count(), is(1L));
    }
    
    private String obtainAccessToken() throws Exception {
        String encodedCredentials = Base64.getEncoder().encodeToString(
                (CLIENT_ID + ":" + CLIENT_SECRET).getBytes());
        String authorizationHeader = "Basic " + encodedCredentials;

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/oauth/token?username="+ USER_EMAIL +
                        "&password=" + USER_PASSWORD + "&grant_type=password")
                .header("Authorization", authorizationHeader)
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
}
