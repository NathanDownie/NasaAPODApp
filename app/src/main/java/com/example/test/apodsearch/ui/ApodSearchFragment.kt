// Declare the package name
package com.example.test.apodsearch.ui

// Import necessary classes
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.ApodSearchBinding

// This is the declaration of the ApodSearchFragment class
class ApodSearchFragment : Fragment() {

    // Declare a property for the view binding object
    private lateinit var binding: ApodSearchBinding

    // This function inflates the ApodSearchBinding layout and returns its root view.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ApodSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    // This function sets up the button click listener and handles the navigation to the next fragment.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set up button click listener
        binding.searchButton.setOnClickListener {
            val datePicker = binding.dateEdit
            val year = datePicker.year
            val month = datePicker.month
            val day = datePicker.dayOfMonth
            val selectedDate = "$year-${month+1}-$day" // +1 because the DatePicker's month is 0-based

            // Pass the selected date to the next fragment
            val bundle = Bundle().apply {
                putString("selectedDate", selectedDate)
            }
            findNavController().navigate(R.id.action_apodSearchFragment_to_apodSearchResultFragment, bundle)
        }
    }

}

















