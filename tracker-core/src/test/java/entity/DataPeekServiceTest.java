package entity;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataPeekServiceTest {

    @Mock
    GPSService gpsService;

    @Test(expected = NullPointerException.class)
    public void put() throws Exception {
        DataPeekService dataPeekService = new DataPeekService(gpsService);
        dataPeekService.put();
    }

    @Test
    public void put1() throws Exception {
        gpsService = new GPSService();
        DataPeekService dataPeekService = new DataPeekService(gpsService);
        dataPeekService.put();
    }

    @Test
    public void take() throws Exception {
        DataPeekService dataPeekService = new DataPeekService(gpsService);
        String str =dataPeekService.take();
        assertEquals("-1",str);
    }

    @Test
    public void take1() throws Exception {
        gpsService = new GPSService();
        DataPeekService dataPeekService = new DataPeekService(gpsService);
        dataPeekService.put();
        String str =dataPeekService.take();
        assertTrue(str.contains("longitude"));
        assertTrue(str.contains("latitude"));
        assertTrue(str.contains("azimuth"));
        assertTrue(str.contains("speed"));
    }

    @Test
    public void queueIsEmpty() throws Exception {
        gpsService = new GPSService();
        DataPeekService dataPeekService = new DataPeekService(gpsService);
        assertTrue(!dataPeekService.queueIsNotEmpty());
        dataPeekService.put();
        assertTrue(dataPeekService.queueIsNotEmpty());
    }

}