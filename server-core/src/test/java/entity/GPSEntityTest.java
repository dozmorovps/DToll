package entity;

import dao.entity.GPSEntity;
import org.junit.Test;

import static org.junit.Assert.*;

public class GPSEntityTest {


//    @Test
//    public void toJson() throws Exception {
//        GPSEntity gpsEntity = new GPSEntity();
//        gpsEntity.longitude = 0.56;
//        gpsEntity.latitude = 0.57;
//        gpsEntity.azimuth = 0.58;
//        gpsEntity.speed = 0.59;
//        String str = gpsEntity.toJson();
//        assertTrue(str.contains("longitude"));
//        assertTrue(str.contains("0.56"));
//        assertTrue(str.contains("{"));
//        assertTrue(str.contains("}"));
//
//    }

    /*@Test
    public void getCoordinate() throws Exception {
        GPSEntity gpsEntity = new GPSEntity();
        gpsEntity.getCoordinate();

        String str = "" + gpsEntity.latitude;
        double tmp = Double.parseDouble(str);
        assertEquals(tmp, gpsEntity.latitude, 0.00002);

        str = "" + gpsEntity.longitude;
        tmp = Double.parseDouble(str);
        assertEquals(tmp, gpsEntity.longitude, 0.00002);

        str = "" + gpsEntity.azimuth;
        tmp = Double.parseDouble(str);
        assertEquals(tmp, gpsEntity.azimuth, 0.00002);

        str = "" + gpsEntity.speed;
        tmp = Double.parseDouble(str);
        assertEquals(tmp, gpsEntity.speed, 0.00002);


    }
*/

}