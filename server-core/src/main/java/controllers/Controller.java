package controllers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller {


    private final RestTemplate restTemplate;

    public Controller(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String relay(@RequestBody String str) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> array = objectMapper.readValue(str, new TypeReference<List<String>>(){});

//        GPSService gpsService = objectMapper.readValue(array.get(0), GPSService.class);
        //тут явно будет дальнейшея обработка
        return "true";
    }


}
