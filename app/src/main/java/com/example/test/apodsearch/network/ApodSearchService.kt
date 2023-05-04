// Declare the package name for this file
package com.example.test.apodsearch.network

// Import necessary classes for the ApodSearchService interface
import com.example.test.ApodData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// This is the declaration of the ApodSearchService interface, which will handle HTTP requests for APOD data from NASA's API
interface ApodSearchService {
    // Define an HTTP GET request that retrieves an astronomy picture of the day (APOD) from the NASA open API using the Retrofit library
    @GET("planetary/apod")
    // Define a function named "getApod" that takes an API key and date as query parameters, and returns a Call object that represents the HTTP request and response
    fun getApod(
        @Query("api_key") apiKey: String, // Query parameter for API key
        @Query("date") date: String // Query parameter for APOD date
    ): Call<ApodData> // The response is deserialized into an ApodData object
}


