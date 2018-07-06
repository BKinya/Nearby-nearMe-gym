package com.beatrice.nearby_nearme_gym.rest;

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

    public static Work_out_session_service getWork_out_session_service(){
        return Api_client.getClient(BASE_URL).create(Work_out_session_service.class);
    }
}


