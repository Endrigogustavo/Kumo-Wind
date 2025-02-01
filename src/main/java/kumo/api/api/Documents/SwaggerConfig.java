package kumo.api.api.Documents;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/*
@OpenAPIDefinition(
    info = @Info(title = "Kumo Wind API", version = "1.0"),
    security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
*/
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Kumo Wind API")
                .description("Kumo Wind é um projeto inovador de galeria de arte online que combina a elegância das nuvens (Kumo, em japonês) com a fluidez do vento (Wind, em inglês). Este projeto permite que artistas compartilhem suas obras em um espaço digital belo e intuitivo, trazendo inspiração e criatividade para todos.")
                .version("1.0")
                .contact(new Contact()
                    .name("Endrigo")
                    .url("https://www.kumowind.com")
                    .email("contato@kumowind.com"))
                .termsOfService("Termos de uso: Kumo Wind")
                .license(new License()
                    .name("Licença - Kumo Wind")
                    .url("https://www.kumowind.com/terms")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes("Bearer Authentication",
                    new SecurityScheme()
                    .name("Bearer Authentication")
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT"))
            );
    }
}
