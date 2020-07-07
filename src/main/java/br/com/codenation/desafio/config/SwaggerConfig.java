package br.com.codenation.desafio.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.title}")
    private String TITLE;

    @Value("${swagger.description}")
    private String DESCRIPTION;

    @Value("${swagger.version}")
    private String VERSION;

	@Value("${swagger.package}")
    private String PACKAGE;
	
	@Value("${swagger.url}")
	private String URL;
	
    @Value("${id}")
    private String OAUTH_CLIENT_ID;

    @Bean
    public Docket apis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(Arrays.asList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                TITLE,
                DESCRIPTION,
                VERSION,
                "N/A",
                null,
                "MIT",
                "N/A",
                new ArrayList<>()
        );
    }

    private SecurityScheme securityScheme() {
        ResourceOwnerPasswordCredentialsGrant grantType = new ResourceOwnerPasswordCredentialsGrant(URL);
        return new OAuthBuilder().name("spring_oauth")
                .grantTypes(Arrays.asList(grantType))
                .build();
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId(OAUTH_CLIENT_ID)
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(new SecurityReference("spring_oauth", scopes())))
                .build();
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("any", "for read operations")
        };
    }
}
