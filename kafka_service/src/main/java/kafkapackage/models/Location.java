package kafkapackage.models;


import java.sql.Timestamp;



public class Location {

    private long location_id;
    private long latitude;
    private long longitude;
//    private Timestamp location_date;


    public Location(long location_id, long latitude, long longitude, Timestamp location_date) {
        this.location_id = location_id;
        this.latitude = latitude;
        this.longitude = longitude;
//        this.location_date = location_date;
    }

    public Location() {

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

//    public Timestamp getLocation_date() {
//        return location_date;
//    }
//
//    public void setLocation_date(Timestamp location_date) {
//        this.location_date = location_date;
//    }

    @Override
    public String toString() {
        return "Location{" +
                "location_id=" + location_id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
//                ", location_date=" + location_date +
                '}';
    }
}
