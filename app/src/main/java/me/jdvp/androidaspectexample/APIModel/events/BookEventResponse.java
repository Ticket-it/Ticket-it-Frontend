package me.jdvp.androidaspectexample.APIModel.events;

public class BookEventResponse{
    private String success;
    private String status;
    private String ticketId;

    public String getStatus() {
        return status;
    }

    public BookEventResponse(String success, String status, String ticketId) {
        this.success = success;
        this.status = status;
        this.ticketId = ticketId;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}