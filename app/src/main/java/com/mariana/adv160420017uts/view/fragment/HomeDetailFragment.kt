package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.FragmentHomeDetailBinding
import com.mariana.adv160420017uts.databinding.FragmentUserDonationBinding
import com.mariana.adv160420017uts.model.Donation
import com.mariana.adv160420017uts.model.DonationHistory
import com.mariana.adv160420017uts.view.ButtonClickInterface
import com.mariana.adv160420017uts.view.ButtonDetailDonateClickListener
import com.mariana.adv160420017uts.view.ButtonDonateClick
import com.mariana.adv160420017uts.view.ProgressBarInterface
import com.mariana.adv160420017uts.viewmodel.HomeDetailViewModel
import com.mariana.adv160420017uts.viewmodel.ProfileViewModel
import java.text.SimpleDateFormat
import java.util.Date

class HomeDetailFragment : Fragment(), ButtonDetailDonateClickListener, ProgressBarInterface, ButtonDonateClick {
    private lateinit var viewModel: HomeDetailViewModel
    private lateinit var profileViewModel: ProfileViewModel
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
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = HomeDetailFragmentArgs.fromBundle(requireArguments()).id
        viewModel = ViewModelProvider(this).get(HomeDetailViewModel::class.java)
        viewModel.fetch(id)

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        observeViewModel()
        dataBinding.detailDonationListener = this
    }

    override fun onDetailDonateClickListener(v: View, donation: Donation) {
        val bottomSheet = BottomSheetDialog(v.context)
        val bindingSheet = DataBindingUtil.inflate<FragmentUserDonationBinding>(layoutInflater, R.layout.fragment_user_donation, null, false)
        bottomSheet.setContentView(bindingSheet.root)

        bindingSheet.donation = donation
        bindingSheet.donateClick = this

        bottomSheet.show()
    }

    override fun progressBarValue(terkumpul: String, target: String): Int {
        return ((terkumpul.toLong() * 100) / target.toInt()).toInt()
    }

    override fun donate(view: View, donation: Donation) {
        val nominal = Integer.parseInt(view.tag.toString())
        val id = donation.id

        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        val current = formatter.format(date)

        val donate = DonationHistory(id.toString(), nominal.toString(), current, profileViewModel.getUserFromSharedPref().username)
        viewModel.addDonate(donate)
        Toast.makeText(view.context, "Donasi Berhasil", Toast.LENGTH_SHORT).show()
    }
}