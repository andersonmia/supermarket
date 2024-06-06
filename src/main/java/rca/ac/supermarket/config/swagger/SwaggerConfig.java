package rca.ac.supermarket.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().addSecurityItem(
                new SecurityRequirement().addList("bearer-key")
                )
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "bearer-key",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .in(SecurityScheme.In.HEADER)
                                                .bearerFormat("JWT")
                                )
                )
                .info(new Info()
                        .title("Supermarket API")
                        .description("Supermarket API Documentation")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Mia")
                                .url("https://www.instagram.com/k.a.m.i.__.s.a.m.a/")
                                .email("andersonmia1968@gmail.com"))
                );
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("supermarket")
                .pathsToMatch("/**")
                .packagesToScan("rca.ac.supermarket.controllers")
                .build();
    }
}
