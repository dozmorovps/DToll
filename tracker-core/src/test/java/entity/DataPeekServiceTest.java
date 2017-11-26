package entity;

import dao.GPSEntity;
import dao.GPSRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import service.DataPeekService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@PropertySource("classpath:app.properties")

public class DataPeekServiceTest {
    @Mock
    GPSEntity gpsEntity;

    @Autowired
    GPSRepository gpsRepository;

    @Test(expected = NullPointerException.class)
    public void put() throws Exception {
        DataPeekService dataPeekService = new DataPeekService(gpsRepository);
        dataPeekService.put();
    }

    @Test
    public void put1() throws Exception {
        gpsEntity = new GPSEntity();
        gpsEntity.getCoordinate();
      //  gpsRepository.save(gpsEntity);
        DataPeekService dataPeekService = new DataPeekService(gpsRepository);

        int id = dataPeekService.put();

    }

   /* @Test
    public void take() throws Exception {
        DataPeekService dataPeekService = new DataPeekService(gpsRepository);
        String str =dataPeekService.take();
        assertEquals("-1",str);
    }

    @Test
    public void take1() throws Exception {
        gpsEntity = new GPSEntity();
        DataPeekService dataPeekService = new DataPeekService(gpsRepository);
        dataPeekService.put();
        String str = dataPeekService.take();
        assertTrue(str.contains("longitude"));
        assertTrue(str.contains("latitude"));
        assertTrue(str.contains("azimuth"));
        assertTrue(str.contains("speed"));
    }
*/

}