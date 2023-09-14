package com.sylph.bobmukja.global.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        /**
         * Swagger 상단 노출정보 커스터마이징
         */
        Info info = new Info().title("API")
                .version("0.0.1")
                .description("API Swagger");

        /**
         * Swagger 대상 서버 설정
         */
        List<Server> servers = Arrays.asList(new Server().url("/").description("API"));

        /**
         * SecurityScheme
         * Request Header Token 설정
         *
         * 활성화를 위해서는 Controller 메소드마다 적용을 해줘야 한다.
         *
         * @SecurityRequirement(name = "bearerAuth")
         * @GetMapping
         */
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .info(info)
                .servers(servers);
    }
}
