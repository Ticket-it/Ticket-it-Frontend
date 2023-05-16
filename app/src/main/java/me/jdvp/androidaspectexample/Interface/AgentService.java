package me.jdvp.androidaspectexample.Interface;

import java.util.ArrayList;
import java.util.List;

import me.jdvp.androidaspectexample.APIModel.agent.AttendanceResponse;
import me.jdvp.androidaspectexample.APIModel.agent.ConfirmationRequest;
import me.jdvp.androidaspectexample.APIModel.agent.ConfirmationResponse;
import me.jdvp.androidaspectexample.APIModel.events.EventResponse;
import me.jdvp.androidaspectexample.APIModel.user.CreateUserRequest;
import me.jdvp.androidaspectexample.APIModel.user.CreateUserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AgentService {
    @GET("attendance/{eventId}")
    Call<ArrayList<AttendanceResponse>> getAttendants(@Path("eventId") String eventId);

    @POST("attendant-approve/{ticketId}")
    Call<ConfirmationResponse> getConfirmation(@Path("ticketId") String ticketId, @Body ConfirmationRequest request);
}
