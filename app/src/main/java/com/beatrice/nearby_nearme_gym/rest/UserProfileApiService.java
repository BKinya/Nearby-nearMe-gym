package com.beatrice.nearby_nearme_gym.rest;

import com.beatrice.nearby_nearme_gym.model.User_profile;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserProfileApiService {

    @FormUrlEncoded
    @POST("api/user_profile")
    Call<User_profile>saveProfile(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("gender") String gender,
            @Field("age") String age,
            @Field("current_weight") String current_weight,
            @Field("target_weight") String target_weight

    );

}
