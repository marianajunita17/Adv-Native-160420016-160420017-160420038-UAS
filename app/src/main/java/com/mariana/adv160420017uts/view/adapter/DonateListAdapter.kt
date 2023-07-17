package com.mariana.adv160420017uts.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.DonasiListItemBinding
import com.mariana.adv160420017uts.model.MyDonation
import com.mariana.adv160420017uts.util.loadImage
import com.mariana.adv160420017uts.view.ButtonDonateClickListener
import com.mariana.adv160420017uts.view.fragment.DonateDetailFragmentDirections
import com.mariana.adv160420017uts.view.fragment.DonateFragmentDirections
import kotlinx.android.synthetic.main.donasi_list_item.view.*

class DonateListAdapter(val myDonationList:ArrayList<MyDonation>)
    :RecyclerView.Adapter<DonateListAdapter.DonasiListViewHolder>(), ButtonDonateClickListener {
    class DonasiListViewHolder(var view: DonasiListItemBinding) : RecyclerView.ViewHolder(view.root)

    fun updateMyDonationList(newMyDonationList: List<MyDonation>){
        myDonationList.clear()
        myDonationList.addAll(newMyDonationList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonasiListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.donasi_list_item, parent, false)
        val view = DataBindingUtil.inflate<DonasiListItemBinding>(inflater, R.layout.donasi_list_item, parent, false)

        return DonasiListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonasiListViewHolder, position: Int) {
        holder.view.txtJudulDonasi.text = myDonationList[position].judul
        holder.view.txtTanggalDonasi.text = myDonationList[position].tanggalDonasi

//        holder.view.btnDonasiSaya.setOnClickListener {
//            val action = DonateFragmentDirections.actionDonateDetail(myDonationList[position].id.toString())
//            Navigation.findNavController(it).navigate(action)
//        }

//        var imageView = holder.view.findViewById<ImageView>(R.id.imgDonasiSaya)
//        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressDonasi)
//        imageView.loadImage(myDonationList[position].photoUrl, progressBar)
    }

    override fun getItemCount(): Int = myDonationList.size

    override fun onButtonDonate(v: View) {
        val id = v.tag.toString()
        val action = DonateFragmentDirections.actionDonateDetail(id)
        Navigation.findNavController(v).navigate(action)
    }
}