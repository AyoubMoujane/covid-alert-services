package com.example.notificationservice.model;

public class UserAlert {

    private long user_id;
    private String message = "Vous avez récemment été en contact avec une personne testée positive à la Covid-19";

    public UserAlert(long user_id) {
        this.user_id = user_id;
    }

    public UserAlert() {}

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserAlert{" +
                "user_id=" + user_id +
                ", message='" + message + '\'' +
                '}';
    }
}
