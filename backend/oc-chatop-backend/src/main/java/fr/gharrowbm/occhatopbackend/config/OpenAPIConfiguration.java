package fr.gharrowbm.occhatopbackend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI chatopOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:3001");
        server.setDescription("Chatop API server");

        Contact contact = new Contact();
        contact.setName("Chatop Team");
        contact.setEmail("contact@chatop.com");

        Info info = new Info();
        info.setTitle("Chatop API");
        info.setVersion("1.0.0");
        info.setDescription("API for Chatop application");
        info.setContact(contact);

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement())
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(info)
                .servers(List.of(server));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}