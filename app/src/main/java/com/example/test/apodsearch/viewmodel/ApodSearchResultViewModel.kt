// Declare the package name
package com.example.test.apodsearch.viewmodel

// Import necessary classes
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test.ApodData
import com.example.test.R
import com.example.test.apodsearch.network.ApodSearchService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// This is the declaration of the  ApodSearchResultViewModel
class ApodSearchResultViewModel(application: Application) : AndroidViewModel(application) {

    // MutableLiveData for holding the APOD data
    private val _apodData = MutableLiveData<ApodData>()
    val apodData: LiveData<ApodData>
        get() = _apodData

    // Create a Retrofit instance for making network requests
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Create an ApodSearchService instance using the Retrofit instance
    private val apodService = retrofit.create(ApodSearchService::class.java)

    // Function for loading APOD data for the selected date
    fun loadApodData(selectedDate: String) {
        // Make a network request using the ApodSearchService instance
        apodService.getApod(
            // Retrieve the API key from the resources
            getApplication<Application>().getString(R.string.api_key),
            // Pass the selected date as a parameter
            selectedDate
        ).enqueue(object : Callback<ApodData> {
            // Handle the network response if it is successful
            override fun onResponse(call: Call<ApodData>, response: Response<ApodData>) {
                if (response.isSuccessful) {
                    // Set the value of the MutableLiveData to the response body
                    _apodData.value = response.body()
                } else {
                    // Handle any unsuccessful response (e.g. API key error)
                }
            }
            // Handle any network request failure (e.g. no internet connection)
            override fun onFailure(call: Call<ApodData>, t: Throwable) {
                // Handle the failure
            }
        })
    }
}



