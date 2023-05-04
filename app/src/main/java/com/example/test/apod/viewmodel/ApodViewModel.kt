// Declare the package name of this code file
package com.example.test.apod.viewmodel

// Import necessary classes
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test.ApodData
import com.example.test.R
import com.example.test.apod.network.ApodService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// This is the declaration of the ApodViewModel class
class ApodViewModel(application: Application) : AndroidViewModel(application) {

    // MutableLiveData holds a value of type ApodData and can be changed
    private val _apodData = MutableLiveData<ApodData>()
    // LiveData is an immutable data holder that can be observed for changes
    val apodData: LiveData<ApodData>
        get() = _apodData

    // Create a Retrofit instance
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Create an instance of the ApodService interface using the Retrofit instance
    private val apodService = retrofit.create(ApodService::class.java)

    fun loadApodData(date: String) {
        apodService.getApod(
            getApplication<Application>().getString(R.string.api_key),
            date
        ).enqueue(object : Callback<ApodData> {
            override fun onResponse(call: Call<ApodData>, response: Response<ApodData>) {
                if (response.isSuccessful) {
                    _apodData.value = response.body()
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<ApodData>, t: Throwable) {
                // Handle error
            }
        })
    }



}










