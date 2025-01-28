package kumo.api.api.Application.Configs.Cors;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  
                .allowedOrigins("http://localhost:3030", "http://localhost:8080")  
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/swagger-ui/**").allowedOrigins("*"); // Permite todos os domínios
                registry.addMapping("/swagger-resources/**").allowedOrigins("*");
                registry.addMapping("/v2/api-docs").allowedOrigins("*");
                registry.addMapping("/v3/api-docs/**").allowedOrigins("*");
                registry.addMapping("/webjars/**").allowedOrigins("*");
            }
        };
    }
}
