package com.example.crew_x.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String BASE_URL = "https://api.spacexdata.com/";

    public static CREWAPI getClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CREWAPI api = retrofit.create(CREWAPI.class);
        return api;
    }
}
