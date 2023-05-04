package com.example.test.apod.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.test.apod.viewmodel.ApodViewModel
import com.example.test.databinding.ApodBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val DATE_FORMAT = "yyyy-MM-dd"

class ApodFragment : Fragment() {

    private var _binding: ApodBinding? = null
    private val binding get() = _binding!!
    private lateinit var glide: RequestManager
    private lateinit var viewModel: ApodViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ApodBinding.inflate(inflater, container, false)

        // Initialize the viewModel using ViewModelProvider
        viewModel = ViewModelProvider(this)[ApodViewModel::class.java]

        // Hide the back button on the action bar
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        // Initialize Glide for image loading
        glide = Glide.with(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe changes in the apodData LiveData object from the ViewModel
        viewModel.apodData.observe(viewLifecycleOwner) { apodData ->

            // Set the title, date, and description TextViews with the values from the apodData object
            binding.apodTitle.text = apodData?.title.orEmpty()
            binding.apodDate.text = apodData?.date.orEmpty()
            binding.apodDescription.text = apodData?.explanation.orEmpty()

            // Load the image into the ImageView using Glide
            apodData?.url?.let { url ->
                glide.load(url)
                    .into(binding.apodImage)
            }
        }

        // Load the apodData for the current date using a coroutine
        viewModel.loadApodData(getCurrentDate())
    }

    // Get the current date in the format specified by DATE_FORMAT
    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return sdf.format(Date())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



















