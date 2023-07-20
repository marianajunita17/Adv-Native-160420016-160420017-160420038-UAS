package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.FragmentHomeBinding
import com.mariana.adv160420017uts.view.SwipeRefreshInterface
import com.mariana.adv160420017uts.view.adapter.HomeListAdapter
import com.mariana.adv160420017uts.viewmodel.HomeListViewModel
import com.mariana.adv160420017uts.viewmodel.ProfileViewModel

class HomeFragment : Fragment(), SwipeRefreshInterface {
    private lateinit var viewModel: HomeListViewModel
    private lateinit var profileViewModel: ProfileViewModel
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
        activity?.let {
            it.findViewById<BottomNavigationView>(R.id.bottomNav).visibility = View.VISIBLE
            it.findViewById<DrawerLayout>(R.id.drawerLayout).setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeListViewModel::class.java)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        if (!profileViewModel.isLogin()) {
            val action = HomeFragmentDirections.actionHomeLogin()
            Navigation.findNavController(view).navigate(action)
        } else {
            viewModel.refresh()

            dataBinding.homeAdapter = homeListAdapter
            dataBinding.homeInterface = this

            observeViewModel()
        }
    }

    override fun onRefresh() {
        viewModel.isRefreshing = true
        viewModel.refresh()
        viewModel.isRefreshing = false
    }
}