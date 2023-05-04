// Declare the package name for this file
package com.example.test.apod.network

// Import necessary classes for the ApodService interface
import com.example.test.ApodData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// This is the declaration of the ApodService interface, which will handle HTTP requests for APOD data from NASA's API
interface ApodService {

    // Annotate this method with GET and specify the endpoint for the HTTP request
    @GET("planetary/apod")
    // Define a function named "getApod" that takes an API key as a required query parameter, and an optional date parameter
    // The function returns a Call object that represents the HTTP request and response, which wraps an ApodData object
    fun getApod(
        @Query("api_key") apiKey: String, // Required query parameter for API key
        @Query("date") date: String = "" // Optional query parameter for APOD date
    ): Call<ApodData> // The response is deserialized into an ApodData object
}












