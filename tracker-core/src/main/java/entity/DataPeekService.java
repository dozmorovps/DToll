package entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Service for data collection and storage
 * @author Дозморов Павел Сергеевич
 */

@Component
public class DataPeekService {


    private final GPSService gpsService;

    private BlockingDeque<String> queue =  new LinkedBlockingDeque<>(100);

    public DataPeekService(@Autowired GPSService gpsService) {
        this.gpsService = gpsService;
    }

    @Scheduled (cron = "${cron.init}")
    void put() throws InterruptedException, JsonProcessingException {
       gpsService.getCoordinate();
       queue.put(gpsService.toJson());
    }

    public String take() throws InterruptedException {
        if(!queue.isEmpty())  return queue.take();
        return "-1";
    }

    public Boolean queueIsNotEmpty()
    {
        return !queue.isEmpty();
    }
}
