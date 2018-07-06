package com.beatrice.nearby_nearme_gym.rest;

import com.beatrice.nearby_nearme_gym.model.Work_out_JSON_response;
import com.beatrice.nearby_nearme_gym.model.Work_out_session_model;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Work_out_session_service {

    @FormUrlEncoded
    @POST("api/workout_sessions")
    Call<Work_out_session_model>add_workout_session(
            @Field("email") String email,
            @Field("date") String date,
            @Field("exercise_type_name") String exercise_type_name,
            @Field("no_of_sets") String no_of_sets);

    @GET("api/workout_sessions/{email}")
    Call<Work_out_JSON_response>getAllssessins(
            @Path("email") String email);
}
