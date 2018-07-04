package com.beatrice.nearby_nearme_gym.rest;



import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api_client {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String base_url) {

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
