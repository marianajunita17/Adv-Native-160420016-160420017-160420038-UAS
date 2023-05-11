package com.mariana.adv160420017uts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.model.MyDonation
import com.mariana.adv160420017uts.util.loadImage
import kotlinx.android.synthetic.main.donasi_list_item.view.*

class DonateListAdapter(val myDonationList:ArrayList<MyDonation>)
    :RecyclerView.Adapter<DonateListAdapter.DonasiListViewHolder>() {
    class DonasiListViewHolder(var view: View) :RecyclerView.ViewHolder(view)

    fun updateMyDonationList(newMyDonationList: ArrayList<MyDonation>){
        myDonationList.clear()
        myDonationList.addAll(newMyDonationList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonasiListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.donasi_list_item, parent, false)

        return DonasiListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonasiListViewHolder, position: Int) {
        holder.view.txtJudulDonasi.text = myDonationList[position].judul
        holder.view.txtTanggalDonasi.text = myDonationList[position].tanggalDonasi

        holder.view.btnDonasiSaya.setOnClickListener {
            val action = DonateFragmentDirections.actionDonateDetail(myDonationList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        var imageView = holder.view.findViewById<ImageView>(R.id.imgDonasiSaya)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressDonasi)
        imageView.loadImage(myDonationList[position].photoUrl, progressBar)
    }

    override fun getItemCount(): Int = myDonationList.size
}