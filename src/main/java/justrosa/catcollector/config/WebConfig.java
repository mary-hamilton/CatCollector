package justrosa.catcollector.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    // the below is allowing the resources under the specified path (in this case, everything on the server
    // to be accessed using the specified methods (DELETE, POST etc).

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("DELETE", "POST", "GET", "PUT", "OPTIONS");
    }
}
