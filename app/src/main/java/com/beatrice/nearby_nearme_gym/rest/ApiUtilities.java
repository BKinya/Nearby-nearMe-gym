package com.beatrice.nearby_nearme_gym.rest;

import retrofit2.Retrofit;

public class ApiUtilities {
    public static final String BASE_URL = "http://nearbynearmegym.herokuapp.com/";

    private ApiUtilities() {

    }

    public static UserApiService getUserApiService(){
        return Api_client.getClient(BASE_URL).create(UserApiService.class);
    }

    public static UserProfileApiService getUserProfileApi(){
        return Api_client.getClient(BASE_URL).create(UserProfileApiService.class);
    }
}


