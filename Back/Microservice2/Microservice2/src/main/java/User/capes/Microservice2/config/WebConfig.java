package User.capes.Microservice2.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configura el CORS para permitir el origen del frontend
        registry.addMapping("/**")  // Configuración global para todas las rutas
                .allowedOrigins("http://localhost:5173")  // Permite el origen de tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
                .allowedHeaders("*")  // Permite todos los headers
                .allowCredentials(true);  // Permite el uso de credenciales
    }
}
