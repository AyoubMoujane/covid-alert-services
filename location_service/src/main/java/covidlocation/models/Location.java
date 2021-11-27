package covidlocation.models;


import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Entity
@Table(name= "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long location_id;
    private double latitude;
    private double longitude;
    private Timestamp location_date;
    private String user_id;

    public Location(long location_id, double latitude, double longitude, Timestamp location_date, String user_id) {
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

    public boolean isCloseto(Location loc, double radius)  {
        return Math.pow(loc.getLatitude()-this.getLatitude(),2) + Math.pow(loc.getLongitude()-this.getLongitude(),2) < Math.pow(radius,2);
    }

    public boolean isMoreRecentThan(long contagionTime)  {
        LocalDateTime now = LocalDateTime.now();
        long daysBetween = ChronoUnit.DAYS.between(this.location_date.toLocalDateTime(),now);
        //long daysBetween = Duration.between(now, this.location_date.toLocalDateTime()).toDays();

        return daysBetween < contagionTime;
    }


}
