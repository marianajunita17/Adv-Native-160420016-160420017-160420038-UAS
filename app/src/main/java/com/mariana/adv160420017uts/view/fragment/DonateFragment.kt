package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.model.Donation
import com.mariana.adv160420017uts.view.ButtonDonateClickListener
import com.mariana.adv160420017uts.view.adapter.DonateListAdapter
import com.mariana.adv160420017uts.viewmodel.DonasiListViewModel
import kotlinx.android.synthetic.main.fragment_donate.*

class DonateFragment : Fragment(),  ButtonDonateClickListener{
    private lateinit var viewModel: DonasiListViewModel
    private lateinit var dataBinding:Fragment
//    private val donateListAdapter = DonateListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_donate,
            container, false)
        // Inflate the layout for this fragment
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        dataBinding.donation = Donation("","","","")
        viewModel = ViewModelProvider(this).get(DonasiListViewModel::class.java)
        dataBinding.listener = this

        recViewDonasi.layoutManager = LinearLayoutManager(context)
        recViewDonasi.adapter = donateListAdapter

        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutDonasi)
        refreshLayout.setOnRefreshListener {
            recViewDonasi.visibility = View.GONE
            progressBarDonasi.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

//        observeViewModel()
    }

    override fun onButtonDonate(v: View) {
        TODO("Not yet implemented")
    }

    //    fun observeViewModel(){
//
//        viewModel.myDonationsLD.observe(viewLifecycleOwner, Observer {
//            donateListAdapter.updateMyDonationList(it)
//        })
//
//        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
//            if(it == true){
//                recViewDonasi.visibility = View.GONE
//                progressBarDonasi.visibility = View.VISIBLE
//            } else {
//                recViewDonasi.visibility = View.VISIBLE
//                progressBarDonasi.visibility = View.GONE
//            }
//        })
//    }
}