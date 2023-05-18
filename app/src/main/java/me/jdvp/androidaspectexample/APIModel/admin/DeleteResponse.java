package me.jdvp.androidaspectexample.APIModel.admin;

public class DeleteResponse {

    private String message;
    private String eventId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public DeleteResponse(String message, String eventId) {
        this.message = message;
        this.eventId = eventId;
    }
}
