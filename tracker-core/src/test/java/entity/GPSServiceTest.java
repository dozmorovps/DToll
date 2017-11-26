package entity;

import dao.GPSEntity;
import org.junit.Test;

import static org.junit.Assert.*;

public class GPSServiceTest {

    @Test
    public void toJson() throws Exception {
        GPSEntity gpsEntity = new GPSEntity();
        gpsEntity.setLatitude(0.56);
        gpsEntity.setLongitude(0.57);
        gpsEntity.setAzimuth(0.58);
        gpsEntity.setSpeed(0.59);
        String str = gpsEntity.toJson();
        assertTrue(str.contains("longitude"));
        assertTrue(str.contains("0.56"));
        assertTrue(str.contains("{"));
        assertTrue(str.contains("}"));

    }

    @Test
    public void getCoordinate() throws Exception {
        GPSEntity gpsEntity = new GPSEntity();
        gpsEntity.getCoordinate();

        String str = "" + gpsEntity.getLatitude();
        double tmp = Double.parseDouble(str);
        assertEquals(tmp, gpsEntity.getLatitude(), 0.00002);

        str = "" + gpsEntity.getLongitude();
        tmp = Double.parseDouble(str);
        assertEquals(tmp, gpsEntity.getLongitude(), 0.00002);

        str = "" + gpsEntity.getAzimuth();
        tmp = Double.parseDouble(str);
        assertEquals(tmp, gpsEntity.getAzimuth(), 0.00002);

        str = "" + gpsEntity.getSpeed();
        tmp = Double.parseDouble(str);
        assertEquals(tmp, gpsEntity.getSpeed(), 0.00002);
    }

}