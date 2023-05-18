package me.jdvp.androidaspectexample.APIModel.admin;

public class EditEventResponse {
    private String message;
    private String eventId;

    public String getMessage() {
        return message;
    }

    public String getEventId() {
        return eventId;
    }

    public EditEventResponse(String message, String eventId) {
        this.message = message;
        this.eventId = eventId;
    }

}
