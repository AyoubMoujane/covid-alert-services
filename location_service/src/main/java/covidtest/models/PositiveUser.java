package covidtest.models;

public class PositiveUser {

    private long user_id;

    public PositiveUser(long user_id) {
        this.user_id = user_id;
    }

    public PositiveUser() {}

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}