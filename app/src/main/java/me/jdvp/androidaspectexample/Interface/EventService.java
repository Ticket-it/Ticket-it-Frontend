package me.jdvp.androidaspectexample.Interface;

import java.util.List;

import me.jdvp.androidaspectexample.APIModel.Events.EventResponse;
import me.jdvp.androidaspectexample.APIModel.Events.EventTypeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EventService {
    @GET("event-types")
    Call<List<EventTypeResponse>> getEventTypes();

    @GET("events/{eventTypeId}")
    Call<List<EventResponse>> getEvents(@Path("eventTypeId") String eventTypeId);



}
