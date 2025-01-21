package kumo.api.api.Documents;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

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
                    .url("https://www.kumowind.com/terms")));
    }
}
