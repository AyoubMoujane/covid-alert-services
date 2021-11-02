package covidlocation.models;


import javax.persistence.*;

@Entity(name="locations")
@Access(AccessType.FIELD)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long location_id;
    private long latitude;
    private long longitude;
    private String location_date;

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

    public String getLocation_date() {
        return location_date;
    }

    public void setLocation_date(String location_date) {
        this.location_date = location_date;
    }
}
