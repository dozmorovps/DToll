package Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Component
public class DataSendService {

    @Autowired
    private DataPeekService dataPeekService;


//
//    @Autowired
//    RestTemplate restTemplate;

    @Scheduled(cron = "${cron.init}")
    public void getPoint() throws InterruptedException {
        System.out.println(dataPeekService.take());
    }

}
