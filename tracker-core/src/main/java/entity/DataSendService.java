package entity;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSendService {

    @Autowired
    private DataPeekService dataPeekService;

    private final RestTemplate restTemplate;

    @Autowired
    private Logger log;

    public DataSendService(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "${cron.output}")
    private void getPoint() throws InterruptedException, JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        List<String> arrayList = new ArrayList<>();
        while(dataPeekService.queueIsNotEmpty()){
            arrayList.add(dataPeekService.take());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        HttpEntity request= new HttpEntity( objectMapper.writeValueAsString(arrayList), headers );
        String returnedUser = restTemplate.postForObject( "http://127.0.0.1:8082/test", request, String.class );
        log.info(returnedUser);
    }
}
