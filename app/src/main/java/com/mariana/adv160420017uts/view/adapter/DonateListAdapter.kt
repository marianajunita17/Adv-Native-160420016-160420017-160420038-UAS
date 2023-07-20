package com.mariana.adv160420017uts.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.DonasiListItemBinding
import com.mariana.adv160420017uts.model.Donation
import com.mariana.adv160420017uts.model.DonationHistory
import com.mariana.adv160420017uts.view.ButtonMyDonateClickListener
import com.mariana.adv160420017uts.view.fragment.DonateFragmentDirections
import com.mariana.adv160420017uts.viewmodel.DonateDetailViewModel

class DonateListAdapter(val donationHistoryList:ArrayList<DonationHistory>, val donationList: ArrayList<Donation>)
    :RecyclerView.Adapter<DonateListAdapter.DonasiListViewHolder>(), ButtonMyDonateClickListener{
    class DonasiListViewHolder(var view: DonasiListItemBinding) : RecyclerView.ViewHolder(view.root)

    fun updateMyDonationList(newDonationHistoryList: List<DonationHistory>){
        donationHistoryList.clear()
        donationHistoryList.addAll(newDonationHistoryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonasiListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<DonasiListItemBinding>(inflater, R.layout.donasi_list_item, parent, false)

        return DonasiListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonasiListViewHolder, position: Int) {
        holder.view.donationDetail = donationHistoryList[position]

        holder.view.donation = donationList.find {
            it.id == donationHistoryList[position].id
        }

        holder.view.detailListener = this
    }

    override fun getItemCount(): Int = donationHistoryList.size

    override fun onButtonMyDonate(v: View) {
        val id = v.tag
        val action = DonateFragmentDirections.actionDonateDetail(id.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }
}