package com.keerk99.ordermanagement.infra.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Order Management API")
                        .version("1.0.0")
                        .description(" API for managing orders, customers and products")
                        .contact(new Contact()
                                .name("Keerk99")
                                .email("antonyhuamantuanama@gmail.com")
                        )
                );
    }
}
