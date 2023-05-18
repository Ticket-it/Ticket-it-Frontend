package me.jdvp.androidaspectexample.APIModel.events;

import java.io.Serializable;

public class EventResponse implements Serializable {
    private int availableTickets;
    private String city;
    private String country;
    private String date;
    private String description;
    private String eventId;
    private String eventName;
    private String imageURL;
    private String location;
    private double price;
    private String time;
    private String type;

    public EventResponse(int availableTickets, String city, String country, String date, String description, String eventId, String eventName, String imageURL, String location, double price, String time, String eventTypeId) {
        this.availableTickets = availableTickets;
        this.city = city;
        this.country = country;
        this.date = date;
        this.description = description;
        this.eventId = eventId;
        this.eventName = eventName;
        this.imageURL = imageURL;
        this.location = location;
        this.price = price;
        this.time = time;
        this.type = eventTypeId;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEventTypeId() {
        return type;
    }

    public void setEventTypeId(String eventTypeId) {
        this.type = eventTypeId;
    }
}
