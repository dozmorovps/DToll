package controllers;


import Services.GPSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/test")
    @ResponseBody
    public String relay(@RequestBody GPSService gpsService){
        return "true";
    }


}
