package me.jdvp.androidaspectexample.Models;

import java.io.Serializable;

public class AttendantModel implements Serializable {

    private String name;

    private AttendantModel(){

    }

    public AttendantModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
