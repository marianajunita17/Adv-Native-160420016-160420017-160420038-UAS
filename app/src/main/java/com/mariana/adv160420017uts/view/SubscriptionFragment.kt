package com.mariana.adv160420017uts.view

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
import com.mariana.adv160420017uts.viewmodel.SubscriptionViewModel
import kotlinx.android.synthetic.main.fragment_donate.*
import kotlinx.android.synthetic.main.fragment_subscription.*

class SubscriptionFragment : Fragment() {
    private  lateinit var viewModel: SubscriptionViewModel
    private val subscriptionAdapter = SubscriptionAdapter(arrayListOf())

    fun observeViewModel(){
        viewModel.subscriptionLD.observe(viewLifecycleOwner, Observer {
            subscriptionAdapter.updateSubscriptionList(it)
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                recViewSubs.visibility = View.GONE
                progressSubscription.visibility = View.VISIBLE
            } else {
                recViewSubs.visibility = View.VISIBLE
                progressSubscription.visibility = View.GONE
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subscription, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SubscriptionViewModel::class.java)
        viewModel.refresh()

        recViewSubs.layoutManager = LinearLayoutManager(context)
        recViewSubs.adapter = subscriptionAdapter

        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutSubs)
        refreshLayout.setOnRefreshListener {
            recViewSubs.visibility = View.GONE
            progressSubscription.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }
}