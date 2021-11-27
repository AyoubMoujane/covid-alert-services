package covidlocation.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Entity
@Table(name= "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long location_id;
    private long latitude;
    private long longitude;
    private Timestamp location_date;
    private String user_id;

    public Location(long location_id, long latitude, long longitude, Timestamp location_date, String user_id) {
        this.location_id = location_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location_date = location_date;
        this.user_id = user_id;
    }
    public Location(){}

    public long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(long location_id) {
        this.location_id = location_id;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public Timestamp getLocation_date() {
        return location_date;
    }

    public void setLocation_date(Timestamp location_date) {
        this.location_date = location_date;
    }
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Location{" +
                "location_id=" + location_id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", location_date=" + location_date +
                ", user_id=" + user_id +
                '}';
    }

    public boolean isCloseto(Location loc, float radius)  {
        return Math.pow(loc.getLatitude()-this.getLatitude(),2) + Math.pow(loc.getLongitude()-this.getLongitude(),2) < Math.pow(radius,2);
    }

    public boolean isMoreRecentThan(long contagionTime)  {
        LocalDateTime now = LocalDateTime.now();
        //long diff = ChronoUnit.DAYS.between(now, this.location_date.toLocalDateTime());
        long daysBetween = Duration.between(now, this.location_date.toLocalDateTime()).toDays();

        return daysBetween < contagionTime;
    }


}
