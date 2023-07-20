package com.mariana.adv160420017uts.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.SubscriptionListItemBinding
import com.mariana.adv160420017uts.model.Subscription

class SubscriptionAdapter(val subscriptionList:ArrayList<Subscription>)
    :RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder>() {
    class SubscriptionViewHolder(var view: SubscriptionListItemBinding): RecyclerView.ViewHolder(view.root)

    fun updateSubscriptionList(newSubscriptionList: ArrayList<Subscription>){
        subscriptionList.clear()
        subscriptionList.addAll(newSubscriptionList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<SubscriptionListItemBinding>(inflater, R.layout.subscription_list_item, parent, false)

        return SubscriptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubscriptionViewHolder, position: Int) {
        holder.view.subscription = subscriptionList[position]
    }

    override fun getItemCount(): Int = subscriptionList.size
}