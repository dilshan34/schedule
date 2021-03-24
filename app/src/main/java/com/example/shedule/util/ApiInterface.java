package com.example.shedule.util;


import com.example.shedule.Models.getsetChart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("getSubTask.php")
    Call<List<getsetChart>> getChartDetails();
}
