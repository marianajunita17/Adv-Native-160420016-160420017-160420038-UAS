package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.FragmentHomeDetailBinding
import com.mariana.adv160420017uts.view.ButtonDetailDonateClickListener
import com.mariana.adv160420017uts.viewmodel.HomeDetailViewModel
import kotlinx.android.synthetic.main.fragment_donate.*

class HomeDetailFragment : Fragment(), ButtonDetailDonateClickListener {
    private lateinit var viewModel: HomeDetailViewModel
    private lateinit var dataBinding: FragmentHomeDetailBinding

    fun observeViewModel() {
        viewModel.donationLD.observe(viewLifecycleOwner){
            dataBinding.homeDetailDonation = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentHomeDetailBinding>(inflater, R.layout.fragment_home_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null){
            val id = HomeDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this).get(HomeDetailViewModel::class.java)
            viewModel.fetch(id.toInt())
        }

        dataBinding.detailDonationListener = this
        observeViewModel()
    }

    override fun onDetailDonateClickListener(v: View) {
        val action = HomeDetailFragmentDirections.actionDetailHome()
        Navigation.findNavController(v).navigate(action)
    }
}