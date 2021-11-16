package covidlocation.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
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




    /*@ManyToOne
    @JoinTable(name="user_locations",joinColumns = @JoinColumn(name="location_id"),inverseJoinColumns = @JoinColumn(name="user_id"))
    private User user;*/



    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



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


}
