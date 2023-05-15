package me.jdvp.androidaspectexample.Interface;

import java.util.ArrayList;
import java.util.List;

import me.jdvp.androidaspectexample.APIModel.agent.AttendanceResponse;
import me.jdvp.androidaspectexample.APIModel.events.EventResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AgentService {
    @GET("attendance/{eventId}")
    Call<ArrayList<AttendanceResponse>> getAttendants(@Path("eventId") String eventId);
}
