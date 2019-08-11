package com.example.teamfinding;

public class InfoSet {
    private String title = "";
    private String email;
    private String topic;

    public InfoSet() {

    }


    public InfoSet(String name, String email, String topic) {
        this.title = name;
        this.email = email;
        this.topic = topic;

    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }
}
