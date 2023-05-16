package me.jdvp.androidaspectexample.APIModel.agent;
import java.io.Serializable;

import me.jdvp.androidaspectexample.APIModel.events.EventDetails;

public class AttendanceResponse{
    private String eventId;
    private String status;
    private String ticketId;
    private String userName;
    private String mobileNo;
    private String email;
    private String userId;
    private EventDetails eventDetails;

    public String getMobileNo() {
        return mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public AttendanceResponse(String eventId, String status, String ticketId, String userName, String mobileNo, String email, EventDetails eventDetails, String userId) {
        this.eventId = eventId;
        this.status = status;
        this.ticketId = ticketId;
        this.userName = userName;
        this.mobileNo = mobileNo;
        this.email = email;
        this.eventDetails = eventDetails;
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }
}
