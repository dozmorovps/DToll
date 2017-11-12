package Services;

import org.junit.Test;

import static org.junit.Assert.*;

public class GPSServiceTest {

    @Test
    public void toJson() throws Exception {
        GPSService gpsService = new GPSService();
        gpsService.longitude = 0.56;
        gpsService.latitude = 0.57;
        gpsService.azimuth = 0.58;
        gpsService.speed = 0.59;
        String str = gpsService.toJson();
        assertTrue(str.contains("longitude"));
        assertTrue(str.contains("0.56"));
        assertTrue(str.contains("{"));
        assertTrue(str.contains("}"));

    }

    @Test
    public void getCoordinate() throws Exception {
        GPSService gpsService = new GPSService();
        gpsService.getCoordinate();
        try{

            String str = ""+gpsService.latitude;
            double tmp = Double.parseDouble(str);

            str = ""+gpsService.longitude;
            tmp = Double.parseDouble(str);

            str = ""+gpsService.azimuth;
            tmp = Double.parseDouble(str);

            str = ""+gpsService.speed;
            tmp = Double.parseDouble(str);

        }catch (NumberFormatException e){}

    }


}