package com.beatrice.nearby_nearme_gym.rest;

import com.beatrice.nearby_nearme_gym.model.LoginResponseObj;
import com.beatrice.nearby_nearme_gym.model.New_user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApiService {
    @FormUrlEncoded
    @POST("api/gymUser")

    Call<New_user>insert_data(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("api/login/{email}/{password}")
    Call <LoginResponseObj>login(@Path("email") String email,
               @Path("password") String password);




}
