package me.jdvp.androidaspectexample.Interface;

import java.util.ArrayList;
import java.util.List;

import me.jdvp.androidaspectexample.APIModel.admin.AddEventTypeRequest;
import me.jdvp.androidaspectexample.APIModel.admin.AddEventTypeResponse;
import me.jdvp.androidaspectexample.APIModel.admin.ApproveAllResponse;
import me.jdvp.androidaspectexample.APIModel.admin.DeleteResponse;
import me.jdvp.androidaspectexample.APIModel.admin.EditEventResponse;
import me.jdvp.androidaspectexample.APIModel.agent.AttendanceResponse;
import me.jdvp.androidaspectexample.APIModel.agent.ConfirmationRequest;
import me.jdvp.androidaspectexample.APIModel.agent.ConfirmationResponse;
import me.jdvp.androidaspectexample.APIModel.events.EventDetails;
import me.jdvp.androidaspectexample.APIModel.events.EventResponse;
import me.jdvp.androidaspectexample.APIModel.events.HistoryMainResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AdminService {
    @POST("event-type/add")
    Call<AddEventTypeResponse> addEventType(@Body AddEventTypeRequest request);

    @GET("event/delete/{eventId}")
    Call<DeleteResponse> deleteEvent(@Path("eventId") String eventId);

    @POST("event/edit/{eventId}")
    Call<EditEventResponse> editEvent(@Path("eventId") String eventId, @Body EventDetails eventDetails);

    @POST("event/add")
    Call<EditEventResponse> addEvent(@Body EventDetails eventDetails);

    @GET("events/{eventTypeId}")
    Call<List<EventResponse>> getEvents(@Path("eventTypeId") String eventTypeId);

    @GET("tickets/{eventId}")
    Call<ArrayList<AttendanceResponse>> getTickets(@Path("eventId") String eventId);

    @GET("tickets-approve-all/{eventId}")
    Call <List<ApproveAllResponse>> approveTickets(@Path("eventId") String eventId);

    @GET("tickets-user/{userId}")
    Call<HistoryMainResponse> getHistory(@Path("userId") String userId);

    @POST("tickets/{ticketId}")
    Call<ConfirmationResponse> confirmBooking(@Path("ticketId") String ticketId, @Body ConfirmationRequest request);
}

