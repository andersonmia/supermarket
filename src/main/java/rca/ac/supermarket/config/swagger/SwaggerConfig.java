package rca.ac.supermarket.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
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
