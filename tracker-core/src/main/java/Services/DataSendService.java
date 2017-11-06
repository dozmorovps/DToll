package Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class DataSendService {

    @Autowired
    private DataPeekService dataPeekService;

    @Autowired
    RestTemplate restTemplate;



    @Scheduled(cron = "${cron.init}")
    public void getPoint() throws InterruptedException {
        System.out.println("UP");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        HttpEntity request= new HttpEntity( dataPeekService.take(), headers );
        String returnedUser = restTemplate.postForObject( "http://127.0.0.1:8082/test", request, String.class );
//        URI uri = restTemplate.postForObject("http://127.0.0.1:8082/test", dataPeekService.take());
        System.out.println(returnedUser);
    }
}