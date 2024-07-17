package com.todo.todoapp.infrastructure.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("나만의 일정 관리 앱 서버 만들기")
                        .description("Todo Application JAVA API Schema")
                        .version("1.0.0")
                );
    }
}
