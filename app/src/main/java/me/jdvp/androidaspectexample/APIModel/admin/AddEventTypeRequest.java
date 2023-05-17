package me.jdvp.androidaspectexample.APIModel.admin;

public class AddEventTypeRequest {
    private String eventTypeName;

    public AddEventTypeRequest(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }
}
