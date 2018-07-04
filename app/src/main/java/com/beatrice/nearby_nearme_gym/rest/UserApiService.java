package com.beatrice.nearby_nearme_gym.rest;

import com.beatrice.nearby_nearme_gym.model.New_user;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.POST;

public interface UserApiService {
    @FormUrlEncoded
    @POST("api/gymUser")

    Call<New_user>insert_data(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );


}
