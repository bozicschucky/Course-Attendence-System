package com.chucky.school.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

  private String devUrl = "http://localhost:9087";

  private String prodUrl = "http://localhost:90870";

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");

    Server prodServer = new Server();
    prodServer.setUrl(prodUrl);
    prodServer.setDescription("Server URL in Production environment");

    Contact contact = new Contact();
    contact.setEmail("teamX@gmail.com");
    contact.setName("teamX");
    contact.setUrl("https://www.teamX.com");

    // License mitLicense = new License().name("MIT
    // License").url("https://choosealicense.com/licenses/mit/");

    Info info = new Info()
        .title("School Management API")
        .version("1.0");

    return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
  }
}