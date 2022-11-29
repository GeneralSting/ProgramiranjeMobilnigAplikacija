package com.example.pma_1.Classes.Network;

import com.example.pma_1.Classes.Models.College;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenCollegeApiService {

    @GET ("/studies/v1/courses?limit=10")
    Call<College> getCollege();
}
