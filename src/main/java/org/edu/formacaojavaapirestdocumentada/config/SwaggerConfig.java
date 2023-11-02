package org.edu.formacaojavaapirestdocumentada.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDetail() {

        return new OpenAPI()
                .info(new Info().title("Title - Rest API")
                        .contact(new io.swagger.v3.oas.models.info.Contact().name("Fabs").email("exemplo@email.com"))
                        .description("API - example of Springboot REST API")
                        .version("1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation());
    }

}
