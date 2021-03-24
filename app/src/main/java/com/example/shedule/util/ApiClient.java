package com.example.shedule.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  ApiClient {

    private static Retrofit retrofit = null;



    public static Retrofit getApiClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.8.143/shedule/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
