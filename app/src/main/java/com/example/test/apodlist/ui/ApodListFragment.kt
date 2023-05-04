// Declare the package name
package com.example.test.apodlist.ui

// Import necessary classes
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.R
import com.example.test.apodlist.adaptor.ApodListItemRecyclerViewAdapter
import com.example.test.apodlist.viewmodel.ApodListViewModel
import com.example.test.databinding.ApodListBinding

// This is the declaration of the ApodListFragment class
class ApodListFragment : Fragment() {

    // Declare variables
    private var _binding: ApodListBinding? = null
    private val binding get() = _binding!!
    private lateinit var apodListAdapter: ApodListItemRecyclerViewAdapter
    private var apodViewModel: ApodListViewModel? = null
    private lateinit var reloadMenuItem: MenuItem

    // This method is called when the fragment is created
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = ApodListBinding.inflate(inflater, container, false)
        val view = binding.root

        setHasOptionsMenu(true) // enable options menu for this fragment

        // Set up the RecyclerView
        val recyclerView = binding.list
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        apodListAdapter = ApodListItemRecyclerViewAdapter(emptyList())
        recyclerView.adapter = apodListAdapter

        // Set up the ViewModel
        this.apodViewModel = ViewModelProvider(this)[ApodListViewModel::class.java]

        // Observe changes in the apodList LiveData and update the adapter
        apodViewModel!!.apodList.observe(viewLifecycleOwner) { apodList ->
            apodListAdapter.setApodList(apodList)
        }

        // Load the initial data
        apodViewModel!!.loadApodListData()
        apodViewModel!!.loadLastApodListData()

        // Return the root view
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar, menu) // inflate the menu
        super.onCreateOptionsMenu(menu, inflater)

        // Get a reference to the reload button
        reloadMenuItem = menu.findItem(R.id.reload_button)
        reloadMenuItem?.isVisible = false // Hide the reload button initially
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.load_button -> {
                this.apodViewModel?.loadApodListData()
                reloadMenuItem?.isVisible = true // Show the reload button
                true
            }

            R.id.reload_button -> {
                this.apodViewModel?.loadLastApodListData()
                true
            }

            else -> {
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                super.onOptionsItemSelected(item)
            }
        }
    }



}




















