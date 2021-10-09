package com.example.crew_x.api;

import com.example.crew_x.model.Crew;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CREWAPI {
    @GET("v4/crew")
    Call<List<Crew>>getCrewList();
}
