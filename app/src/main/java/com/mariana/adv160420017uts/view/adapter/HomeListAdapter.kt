package com.mariana.adv160420017uts.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.HomeListItemBinding
import com.mariana.adv160420017uts.model.Donation
import com.mariana.adv160420017uts.view.ButtonDonateClickListener
import com.mariana.adv160420017uts.view.ProgressBarInterface
import com.mariana.adv160420017uts.view.fragment.HomeFragmentDirections


class HomeListAdapter(val donationList:ArrayList<Donation>)
    :RecyclerView.Adapter<HomeListAdapter.HomeViewHolder>(), ButtonDonateClickListener, ProgressBarInterface {
    class HomeViewHolder(var view: HomeListItemBinding) : RecyclerView.ViewHolder(view.root)

    fun updateDonationList(newDonationList: List<Donation>) {
        donationList.clear()
        donationList.addAll(newDonationList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.home_list_item, parent, false)
        val view = DataBindingUtil.inflate<HomeListItemBinding>(
            inflater,
            R.layout.home_list_item,
            parent,
            false
        )
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.view.donate = donationList[position]
        holder.view.donateListListener = this
        holder.view.progressBarHorizontal = this
    }

    override fun getItemCount(): Int = donationList.size

    override fun onDonateClickListener(v: View) {
        val id = v.tag.toString().toInt()
        val action = HomeFragmentDirections.actionHomeDetail(id)
        Navigation.findNavController(v).navigate(action)
    }

    override fun progressBarValue(terkumpul: String, target: String): Int {
        return ((terkumpul.toLong() * 100) / target.toInt()).toInt()
    }
}