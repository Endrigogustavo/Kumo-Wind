package kumo.api.api.Application.Configs;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  
                .allowedOrigins("http://localhost:3000", "http://localhost:8080")  
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
