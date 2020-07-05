package br.com.codenation.desafio.controller;

import br.com.codenation.desafio.config.AuthenticationServerConfiguration;
import br.com.codenation.desafio.constants.PatchMediaType;
import br.com.codenation.desafio.enums.Environment;
import br.com.codenation.desafio.enums.Level;
import br.com.codenation.desafio.enums.Status;
import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.model.User;
import br.com.codenation.desafio.repository.LogRepository;
import br.com.codenation.desafio.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LogControllerTest {

    public static final String LOG_TITLE_1 = "Log Title 1";
    public static final String DESCRIPTION_1 = "Description 1";
    public static final String API_TEST = "Api test";
    public static final Environment ENVIRONMENT = Environment.PRODUCTION;
    public static final Status STATUS = Status.ACTIVE;
    public static final Level LEVEL = Level.ERROR;
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private AuthenticationServerConfiguration authenticationServerConfiguration;

    @Autowired
    private UserRepository userRepository;

    private Log log;

    private MockMvc mockMvc;

    private final String userEmail = "oauth@teste.com";
    private final String userPassword = "password";

    @Before
    @Transactional
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        User user = User.builder()
                .email("teste@test.com")
                .nome("teste")
                .password("senha")
                .createdAt(LocalDateTime.now())
                .token("1234")
                .build();

        this.log = logRepository.save(Log.builder()
                .title(LOG_TITLE_1)
                .description(DESCRIPTION_1)
                .origin(API_TEST)
                .environment(ENVIRONMENT)
                .lastOccurrence(LocalDateTime.now())
                .status(STATUS)
                .level(LEVEL)
                .user(user)
                .build());

    }

    @Test
    public void Patch() throws Exception {
        String content = "'{'\"status\": \"{0}\"'}'";
        String format = MessageFormat.format(content, Status.EXCLUDED.toString());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.patch("/log/" + log.getId())
                .contentType(PatchMediaType.APPLICATION_MERGE_PATCH)
                .content(format))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("EXCLUDED"));
    }
}
