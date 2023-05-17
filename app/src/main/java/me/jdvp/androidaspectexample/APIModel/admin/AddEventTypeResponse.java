package me.jdvp.androidaspectexample.APIModel.admin;

public class AddEventTypeResponse {
    private String message;
    private String eventTypeId;

    public AddEventTypeResponse(String message, String eventTypeId) {
        this.message = message;
        this.eventTypeId = eventTypeId;
    }


    public String getMessage() {
        return message;
    }

    public String getEventTypeId() {
        return eventTypeId;
    }
}
