package controllers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.GPSRepository;
import dao.entity.GPSEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    GPSRepository gpsRepository;

    private final RestTemplate restTemplate;

    public Controller(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String relay(@RequestBody String str) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<GPSEntity> array = objectMapper.readValue(str, new TypeReference<List<GPSEntity>>() {
        });
        gpsRepository.save(array);

        return "true";
    }

}
