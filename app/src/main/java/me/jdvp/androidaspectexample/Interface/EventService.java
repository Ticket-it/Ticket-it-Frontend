package me.jdvp.androidaspectexample.Interface;

import java.io.Serializable;
import java.util.List;

import me.jdvp.androidaspectexample.APIModel.Events.BookEventResponse;
import me.jdvp.androidaspectexample.APIModel.Events.EventResponse;
import me.jdvp.androidaspectexample.APIModel.Events.EventTypeResponse;
import me.jdvp.androidaspectexample.APIModel.Events.HistoryMainResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EventService {
    @GET("event-types")
    Call<List<EventTypeResponse>> getEventTypes();

    @GET("events/{eventTypeId}")
    Call<List<EventResponse>> getEvents(@Path("eventTypeId") String eventTypeId);

    @GET("events/book/{eventId}/{userId}")
    Call<BookEventResponse> bookEvent(@Path("eventId") String eventId, @Path("userId") String userId);

    @GET("history/{userId}")
    Call<HistoryMainResponse> getHistory(@Path("userId") String userId);

}
