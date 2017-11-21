package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.GPSEntity;
import dao.GPSRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Service for data collection and storage
 *
 * @author Дозморов Павел Сергеевич
 */

@Component
public class DataPeekService {

    @Autowired
    private Logger log;

    int lastPoint;

    private final GPSRepository gpsRepository;

    public DataPeekService(@Autowired GPSRepository gpsRepository) {
        this.gpsRepository = gpsRepository;
    }

    @Scheduled(cron = "${cron.init}")
    public int put() {
        GPSEntity gpsEntity = new GPSEntity();
        gpsEntity.getCoordinate();
        GPSEntity tmp = gpsRepository.save(gpsEntity);
        return lastPoint=tmp.getId();
    }


    private GPSEntity createGPS(String model) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        GPSEntity gpsEntity = objectMapper.readValue(model, new TypeReference<GPSEntity>() {});
        GPSEntity tmp = new GPSEntity();
        tmp.setModel(gpsEntity);
        return gpsRepository.save(tmp);

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
