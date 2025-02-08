package fr.gharrowbm.occhatopbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
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

        return new OpenAPI().info(info).servers(List.of(server));
    }
}