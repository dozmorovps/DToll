package Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class GPSService {

    public double speed; //скорость

    public double latitude; // широта

    public double longitude; // долгота

    public double azimuth; // азимут



    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }


    public void getCoordinate() {
        Random rd = new Random();
        speed = rd.nextDouble();
        latitude = rd.nextDouble();
        longitude = rd.nextDouble();
        azimuth = rd.nextDouble();
    }
}
