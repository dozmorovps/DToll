package main;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.GPSRepository;
import dao.USERRepository;
import dao.entity.GPSEntity;
import dao.entity.USEREntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
@ComponentScan({"main", "controllers", "dao"})
@EnableJpaRepositories("dao")
@PropertySource("classpath:app.properties")
@EntityScan("dao.entity")
public class MainSC implements CommandLineRunner {

    public static void main(String... args) {
        SpringApplication.run(MainSC.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @Primary
    public Logger logger() {
        return Logger.getLogger("LoggerServerCore");
    }

    @Autowired
    GPSRepository gpsRepository;

    @Autowired
    USERRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        String str = "{\"id\":1,\"speed\":12.0,\"latitude\":12.0,\"longitude\":12.0,\"azimuth\":12.0,\"user_ID\":1}";
        GPSEntity gps1 = createGPS(str);
        readGPS();
        log.info("______________________________________________________________");
        String str1 = "{\"id\":1,\"speed\":12.0,\"latitude\":13.0,\"longitude\":13.0,\"azimuth\":13.0,\"user_ID\":1}";
        updateGPS(gps1, str1);
        readGPS();

        readUsers();
        log.info("______________________________________________________________");
        USEREntity user1 = createUser("pavel", "dozmorov");
        readUsers();
        log.info("______________________________________________________________");
        deleteUser(user1);
        readUsers();
        log.info("______________________________________________________________");

    }

    @Autowired
    private Logger log;

    private USEREntity createUser(String name, String sename) {
        USEREntity userEntity = new USEREntity();
        userEntity.setName(name);
        userEntity.setSename(sename);
        return userRepository.save(userEntity);
    }

    private void readUsers() {
        List<USEREntity> all = (List<USEREntity>) userRepository.findAll();

        if (all.size() == 0) {
            log.info("нет строки");
        } else {
            all.stream().forEach(gpsEntity -> log.info(gpsEntity.toString()));
        }
    }

    private void updateUser(USEREntity userEntity, String str) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        USEREntity userEntity1 = objectMapper.readValue(str, new TypeReference<GPSEntity>() {
        });
        userEntity.setModel(userEntity1);
        userRepository.save(userEntity);
    }

    private void deleteUser(USEREntity userEntity) {
        userRepository.delete(userEntity);
    }


    private GPSEntity createGPS(String model) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        GPSEntity gpsEntity = objectMapper.readValue(model, new TypeReference<GPSEntity>() {
        });
        USEREntity userEntity = userRepository.findOne(gpsEntity.getUSER_ID());
        if (userEntity != null) {
            GPSEntity tmp = new GPSEntity();
            tmp.setModel(gpsEntity);
            return gpsRepository.save(tmp);
        } else return null;
    }

    private void readGPS() {
        List<GPSEntity> all = (List<GPSEntity>) gpsRepository.findAll();

        if (all.size() == 0) {
            log.info("нет строки");
        } else {
            all.stream().forEach(gpsEntity -> log.info(gpsEntity.toString()));
        }
    }

    private void updateGPS(GPSEntity gpsEntity, String model) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        GPSEntity gpsEntity1 = objectMapper.readValue(model, new TypeReference<GPSEntity>() {
        });
        gpsEntity.setModel(gpsEntity1);
        gpsRepository.save(gpsEntity);
    }

    private void deleteGPS(GPSEntity gpsEntity) {
        gpsRepository.delete(gpsEntity);
    }

}
