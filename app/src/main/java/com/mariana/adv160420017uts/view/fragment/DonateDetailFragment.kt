package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.FragmentDonateDetailBinding
import com.mariana.adv160420017uts.util.loadImage
import com.mariana.adv160420017uts.view.ButtonDetailDonateClickListener
import com.mariana.adv160420017uts.view.ButtonMyDetailDonateClickListener
import com.mariana.adv160420017uts.viewmodel.DonateDetailViewModel
import kotlinx.android.synthetic.main.fragment_donate.*
import kotlinx.android.synthetic.main.fragment_donate_detail.*

class DonateDetailFragment : Fragment(), ButtonMyDetailDonateClickListener{
    private lateinit var viewModel: DonateDetailViewModel
    private lateinit var dataBinding: FragmentDonateDetailBinding

    fun observeViewModel(){
        viewModel.myDonationLD.observe(viewLifecycleOwner){
            dataBinding.donateDetail = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentDonateDetailBinding>(inflater, R.layout.fragment_donate_detail,
            container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments!=null){
            var id = DonateDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this).get(DonateDetailViewModel::class.java)
            viewModel.fetch(id)
        }

        dataBinding.donationListener = this
        observeViewModel()
    }

    override fun onDetailMyDonateClickListener(v: View) {
        val action = DonateDetailFragmentDirections.actionDetailDonate()
        Navigation.findNavController(v).navigate(action)
    }
}