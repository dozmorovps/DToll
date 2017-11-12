package Services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
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
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSendService {

    @Autowired
    private DataPeekService dataPeekService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Logger log;


    @Scheduled(cron = "${cron.output}")
    public void getPoint() throws InterruptedException, JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        List<String> arrayList = new ArrayList<>();
        while(dataPeekService.queueIsEmpty()){
            arrayList.add(dataPeekService.take());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        HttpEntity request= new HttpEntity( objectMapper.writeValueAsString(arrayList), headers );
        String returnedUser = restTemplate.postForObject( "http://127.0.0.1:8082/test", request, String.class );
        log.info(returnedUser);
    }
}
