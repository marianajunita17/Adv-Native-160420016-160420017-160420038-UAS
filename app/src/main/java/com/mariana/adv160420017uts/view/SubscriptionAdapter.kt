package com.mariana.adv160420017uts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.model.Subscription
import com.mariana.adv160420017uts.util.loadImage
import kotlinx.android.synthetic.main.home_list_item.view.*
import kotlinx.android.synthetic.main.subscription_list_item.view.*

class SubscriptionAdapter(val subscriptionList:ArrayList<Subscription>)
    :RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder>() {
    class SubscriptionViewHolder(var view: View): RecyclerView.ViewHolder(view)

    fun updateSubscriptionList(newSubscriptionList: ArrayList<Subscription>){
        subscriptionList.clear()
        subscriptionList.addAll(newSubscriptionList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.subscription_list_item, parent, false)

        return SubscriptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubscriptionViewHolder, position: Int) {
        holder.view.txtJudulSubs.text = subscriptionList[position].title
        holder.view.txtUang.text = "Rp" + subscriptionList[position].dana + "/Bulan"

        var imgView = holder.view.findViewById<ImageView>(R.id.imgSubscription)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressSubsItem)
        imgView.loadImage(subscriptionList[position].photoUrl, progressBar)
    }

    override fun getItemCount(): Int = subscriptionList.size
}