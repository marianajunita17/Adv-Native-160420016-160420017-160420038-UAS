package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.FragmentDonateBinding
import com.mariana.adv160420017uts.view.SwipeRefreshInterface
import com.mariana.adv160420017uts.view.adapter.DonateListAdapter
import com.mariana.adv160420017uts.viewmodel.DonasiListViewModel
import com.mariana.adv160420017uts.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_donate.*

class DonateFragment : Fragment(), SwipeRefreshInterface {
    private lateinit var viewModel: DonasiListViewModel
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentDonateBinding
    private val donateListAdapter = DonateListAdapter(arrayListOf(), arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_donate, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DonasiListViewModel::class.java)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        dataBinding.donateAdapter = donateListAdapter
        viewModel.refresh(profileViewModel.getUserFromSharedPref())
        dataBinding.donasiInterface = this

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.donationsLDHistory.observe(viewLifecycleOwner){
            donateListAdapter.updateMyDonationList(it)
        }
    }

    override fun onRefresh() {
        viewModel.isRefreshing = true
        viewModel.refresh(profileViewModel.getUserFromSharedPref())
        viewModel.isRefreshing = false
    }
}