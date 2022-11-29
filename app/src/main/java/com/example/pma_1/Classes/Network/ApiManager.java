package com.example.pma_1.Classes.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    static ApiManager instance;
    private OpenCollegeApiService service;

    private ApiManager() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl("https://data.uio.no")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(OpenCollegeApiService.class);
    }

    public static ApiManager getInstance() {
        if(instance == null)
            instance = new ApiManager();
        return instance;
    }

    public OpenCollegeApiService service() {
        return service;
    }
}
