package com.example.test.apodlist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test.ApodData
import com.example.test.R
import com.example.test.apodlist.network.ApodListService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ApodListViewModel(application: Application) : AndroidViewModel(application) {

    private val _apodList: MutableLiveData<List<ApodData>> = MutableLiveData()

    val apodList: LiveData<List<ApodData>>
        get() = _apodList

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apodListService: ApodListService = retrofit.create(ApodListService::class.java)

    private var startDate: String = getCurrentDate()
    private var endDate: String = startDate
    private var lastStartDate: String? = null

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun loadApodListData() {
        _isLoading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            try {
                endDate = lastStartDate ?: endDate
                startDate = (7).getPastDate(endDate)
                val apiKey = getApplication<Application>().getString(R.string.api_key)
                val call = apodListService.getApodList(apiKey, startDate, endDate)
                call.enqueue(object : Callback<List<ApodData>> {
                    override fun onResponse(
                        ignoredCall: Call<List<ApodData>>,
                        response: Response<List<ApodData>>
                    ) {
                        _isLoading.value = false
                        if (response.isSuccessful) {
                            val apodList = response.body()?.reversed() ?: emptyList()
                            val newApodList = apodList
                            _apodList.postValue(newApodList)
                            lastStartDate = startDate
                        } else {
                            // Handle the error if the response is not successful
                        }
                    }

                    override fun onFailure(ignoredCall: Call<List<ApodData>>, t: Throwable) {
                        _isLoading.value = false
                        // Handle the error if the request fails
                    }
                })
            } catch (_: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun loadLastApodListData() {
        _isLoading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            try {
                startDate = lastStartDate ?: endDate
                endDate = (7).getFutureDate(endDate)
                val apiKey = getApplication<Application>().getString(R.string.api_key)
                val call = apodListService.getApodList(apiKey, startDate, endDate)
                call.enqueue(object : Callback<List<ApodData>> {
                    override fun onResponse(
                        ignoredCall: Call<List<ApodData>>,
                        response: Response<List<ApodData>>
                    ) {
                        _isLoading.value = false
                        if (response.isSuccessful) {
                            val apodList = response.body()?.reversed() ?: emptyList()
                            val newApodList = apodList
                            _apodList.postValue(newApodList)
                            lastStartDate = startDate
                        } else {
                            // Handle the error if the response is not successful
                        }
                    }

                    override fun onFailure(ignoredCall: Call<List<ApodData>>, t: Throwable) {
                        _isLoading.value = false
                        // Handle the error if the request fails
                    }
                })
            } catch (_: Exception) {
                _isLoading.value = false
            }
        }
    }

}

private fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(Calendar.getInstance().time)
}

private fun Int.getPastDate(endDate: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.time = dateFormat.parse(endDate)!!
    calendar.add(Calendar.DAY_OF_MONTH, -this)
    return dateFormat.format(calendar.time)
}

private fun Int.getFutureDate(endDate: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.time = dateFormat.parse(endDate)!!
    calendar.add(Calendar.DAY_OF_MONTH, this)
    return dateFormat.format(calendar.time)
}




































