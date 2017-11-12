package Main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;


@ComponentScan({"Main","controllers","Services"})
@PropertySource("classpath:app.properties")
@SpringBootApplication
public class MainSC {

    public static void main(String...args){
         SpringApplication.run(MainSC.class);
     }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

}
