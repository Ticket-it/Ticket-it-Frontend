package me.jdvp.androidaspectexample.APIModel.admin;

public class ApproveAllResponse {
    private String eventId;
    private String status;
    private String ticketId;
    private String userId;

    public ApproveAllResponse(String eventId, String status, String ticketId, String userId) {
        this.eventId = eventId;
        this.status = status;
        this.ticketId = ticketId;
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getStatus() {
        return status;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getUserId() {
        return userId;
    }
}
