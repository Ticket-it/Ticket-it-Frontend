package me.jdvp.androidaspectexample.Models;

import java.util.List;

public class EventTypeModel {
    private String image;
    private List<EventModel> events;

    public EventTypeModel() {
    }

    public EventTypeModel(String image, List<EventModel> events) {
        this.image = image;
        this.events = events;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<EventModel> getEvents() {
        return events;
    }

    public void setEvents(List<EventModel> events) {
        this.events = events;
    }
}
