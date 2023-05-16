package me.jdvp.androidaspectexample.Models;

import java.io.Serializable;

public class EventModel implements Serializable {
    private String title;
    private String address; // Family Park, Fifth Settlement
    private String image;
    private int price;
    private String description;
    private String date;
    private String time;
    private String location; // Cairo, Egypt

    public EventModel(String title, String address, String image, int price, String description, String date, String time, String location) {
        this.title = title;
        this.address = address;
        this.image = image;
        this.price = price;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
    }


    // getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
