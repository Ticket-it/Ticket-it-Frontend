package me.jdvp.androidaspectexample.APIModel.events;

public class TicketsHistoryResponse {
    private String eventId;
    private String status;
    private String ticketId;
    private EventDetails eventDetails;

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public TicketsHistoryResponse(String eventId, String status, String ticketId, EventDetails eventDetails) {
        this.eventId = eventId;
        this.status = status;
        this.ticketId = ticketId;
        this.eventDetails = eventDetails;
    }

    public String getEventId() {
        return eventId;
    }

    public String getStatus() {
        return status;
    }
}