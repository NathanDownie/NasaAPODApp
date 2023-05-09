package com.example.test.apodlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.R
import com.example.test.apodlist.adaptor.ApodListItemRecyclerViewAdapter
import com.example.test.apodlist.viewmodel.ApodListViewModel
import com.example.test.databinding.ApodListBinding

class ApodListFragment : Fragment() {

    private val apodViewModel: ApodListViewModel by viewModels()
    private lateinit var apodListAdapter: ApodListItemRecyclerViewAdapter
    private var _binding: ApodListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ApodListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true) // Enable options menu for this fragment
        initRecyclerView()
        observeApodList()
        apodViewModel.loadApodListData() // Load data when the fragment starts
        return binding.root
    }

    private fun initRecyclerView() {
        apodListAdapter = ApodListItemRecyclerViewAdapter(emptyList())
        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = apodListAdapter
        }
    }

    private fun observeApodList() {
        apodViewModel.apodList.observe(viewLifecycleOwner) { apodList ->
            apodListAdapter.setApodList(apodList)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar, menu) // Inflate the menu
        super.onCreateOptionsMenu(menu, inflater)
        val reloadMenuItem = menu.findItem(R.id.reload_button)
        reloadMenuItem.isVisible = apodViewModel.isLoading.value == true // Show the reload button if loading is in progress
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.load_button -> {
                apodViewModel.loadApodListData()
                true
            }
            R.id.reload_button -> {
                apodViewModel.loadApodListData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}






















