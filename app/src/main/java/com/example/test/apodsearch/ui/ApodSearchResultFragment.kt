package com.example.test.apodsearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.test.R
import com.example.test.apodsearch.viewmodel.ApodSearchResultViewModel
import com.example.test.databinding.ApodBinding

class ApodSearchResultFragment : Fragment() {

    private lateinit var viewModel: ApodSearchResultViewModel
    private var _binding: ApodBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ApodBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ApodSearchResultViewModel::class.java]

        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedDate = requireArguments().getString("selectedDate")

        viewModel.apodData.observe(viewLifecycleOwner) { apodData ->
            binding.apodTitle.text = apodData?.title ?: ""
            binding.apodDate.text = apodData?.date ?: ""
            binding.apodDescription.text = apodData?.explanation ?: ""
            apodData?.url?.let { url ->
                Glide.with(this)
                    .load(url)
                    .into(binding.apodImage)
            }
        }

        if (selectedDate != null) {
            viewModel.loadApodData(selectedDate)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigate(R.id.action_apodSearchResultFragment_to_apodSearchFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}






