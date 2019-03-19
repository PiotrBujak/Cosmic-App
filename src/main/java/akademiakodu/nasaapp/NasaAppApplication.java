package akademiakodu.nasaapp;

import akademiakodu.nasaapp.models.Planet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:config.properties")
@SpringBootApplication
public class NasaAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(NasaAppApplication.class, args);
    }

}
