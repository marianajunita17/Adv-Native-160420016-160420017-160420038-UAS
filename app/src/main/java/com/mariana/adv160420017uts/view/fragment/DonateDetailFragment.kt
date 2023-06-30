package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.util.loadImage
import com.mariana.adv160420017uts.viewmodel.DonateDetailViewModel
import kotlinx.android.synthetic.main.fragment_donate_detail.*

class DonateDetailFragment : Fragment() {
    private lateinit var viewModel: DonateDetailViewModel

    fun observeViewModel(){
        viewModel.myDonationLD.observe(viewLifecycleOwner, Observer {
            imgDetailDonasi.loadImage(it.photoUrl, progressDonateDetail)
            txtJudulDonasiDetail.setText(it.judul)
            txtTglDonasi.setText(it.tanggalDonasi)
            txtStatus.setText(it.status)
            txtTotal.setText(it.totalDonasi)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donate_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = DonateDetailFragmentArgs.fromBundle(requireArguments()).id

        viewModel = ViewModelProvider(this).get(DonateDetailViewModel::class.java)
        viewModel.fetch(id)

        btnDonasiLagi.setOnClickListener {
            val action = DonateDetailFragmentDirections.actionDetailDonate()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }
}