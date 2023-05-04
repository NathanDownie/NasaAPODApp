package com.example.test.apodlist.network

import com.example.test.ApodData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodListService {

    @GET("planetary/apod")
    fun getApodList(
        @Query("api_key") apiKey: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): Call<List<ApodData>>
}

