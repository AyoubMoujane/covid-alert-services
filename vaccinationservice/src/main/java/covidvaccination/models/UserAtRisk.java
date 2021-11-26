package covidvaccination.models;

public class UserAtRisk {
    private String user_id;

    public UserAtRisk(String user_id) {
        this.user_id = user_id;
    }

    public UserAtRisk() {}

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "UserAtRisk{" +
                "user_id=" + user_id +
                '}';
    }
}
