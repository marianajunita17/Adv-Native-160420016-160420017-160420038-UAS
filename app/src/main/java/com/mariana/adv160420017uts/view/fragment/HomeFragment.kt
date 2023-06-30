package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.view.adapter.HomeListAdapter
import com.mariana.adv160420017uts.viewmodel.HomeListViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private  lateinit var viewModel: HomeListViewModel
    private val homeListAdapter = HomeListAdapter(arrayListOf())

    fun observeViewModel(){
        viewModel.donationsLD.observe(viewLifecycleOwner, Observer {
            homeListAdapter.updateDonationList(it)
        })

        viewModel.donationLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                txtError.visibility = View.VISIBLE
            } else {
                txtError.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                recyclerView.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeListViewModel::class.java)
        viewModel.refresh()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = homeListAdapter

        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        refreshLayout.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }
}