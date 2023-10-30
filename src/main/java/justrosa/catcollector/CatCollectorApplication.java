package justrosa.catcollector;

import justrosa.catcollector.properties.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({RsaKeyProperties.class})
@SpringBootApplication
public class CatCollectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatCollectorApplication.class, args);
    }

}
