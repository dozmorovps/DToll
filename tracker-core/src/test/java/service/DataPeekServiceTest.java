package service;

import config.TestConfig;
import dao.GPSEntity;
import dao.GPSRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@ActiveProfiles("test")
@PropertySource("classpath:appTest.properties")
public class DataPeekServiceTest {

    @Autowired
    GPSRepository gpsRepository;

    @Mock
    GPSEntity gpsEntity;

    @Test
    public void put() throws Exception {
        gpsEntity = new GPSEntity();

        List<GPSEntity> list = (List<GPSEntity>) gpsRepository.findAll();
        list.stream().forEach(gpsEntity1 -> System.out.println(gpsEntity1));
    }

}