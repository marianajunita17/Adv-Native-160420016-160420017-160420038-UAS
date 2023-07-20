package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.FragmentHomeBinding
import com.mariana.adv160420017uts.view.HomePageInterface
import com.mariana.adv160420017uts.view.adapter.HomeListAdapter
import com.mariana.adv160420017uts.viewmodel.HomeListViewModel

class HomeFragment : Fragment(), HomePageInterface {
    private lateinit var viewModel: HomeListViewModel
    private lateinit var dataBinding: FragmentHomeBinding
    private val homeListAdapter = HomeListAdapter(arrayListOf())

    private fun observeViewModel(){
        viewModel.donationsLD.observe(viewLifecycleOwner) {
            homeListAdapter.updateDonationList(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeListViewModel::class.java)
        viewModel.refresh()

        dataBinding.homeAdapter = homeListAdapter
        dataBinding.homeInterface = this

        observeViewModel()
    }

    override fun onRefresh() {
        viewModel.isRefreshing = true
        viewModel.refresh()
        viewModel.isRefreshing = false
    }
}