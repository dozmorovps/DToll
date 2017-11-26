package dao.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.Random;


@Entity
@Table(name = "GPS")
public class GPSEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    int id;

    @Column(name = "speed")
    double speed; //скорость

    @Column(name = "latitude")
    double latitude; // широта

    @Column(name = "longitude")
    double longitude; // долгота

    @Column(name = "azimuth")
    double azimuth; // азимут

    @Column(name = "USER_ID")
    private int USER_ID;

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public void setModel(GPSEntity model) {
        this.latitude = model.latitude;
        this.longitude = model.longitude;
        this.azimuth = model.azimuth;
        this.speed = model.speed;
        this.USER_ID = model.USER_ID;
    }

    public void getCoordinate() {
        Random rd = new Random();
        speed = rd.nextDouble();
        latitude = rd.nextDouble();
        longitude = rd.nextDouble();
        azimuth = rd.nextDouble();
        USER_ID =1;
    }


}
