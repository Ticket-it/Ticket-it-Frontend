package me.jdvp.androidaspectexample.APIModel.Events;

public class EventDetails {
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
    private String eventTypeId;

    public int getAvailableTickets() {
        return availableTickets;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }

    public String getTime() {
        return time;
    }

    public String getEventTypeId() {
        return eventTypeId;
    }

    public EventDetails(int availableTickets, String city, String country, String date, String description, String eventId, String eventName, String imageURL, String location, double price, String time, String eventTypeId) {
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
        this.eventTypeId = eventTypeId;
    }
}
