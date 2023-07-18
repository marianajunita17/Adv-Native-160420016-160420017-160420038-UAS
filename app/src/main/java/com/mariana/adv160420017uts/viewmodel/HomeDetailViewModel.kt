package com.mariana.adv160420017uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mariana.adv160420017uts.model.Donation
import com.mariana.adv160420017uts.model.MyDonation
import com.mariana.adv160420017uts.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeDetailViewModel(application: Application): AndroidViewModel(application), CoroutineScope {

    val donationLD = MutableLiveData<Donation>()
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun addDonate(myDonation: MyDonation){
        launch {
            val db = buildDb(getApplication())
            db.myDonationDao().donate(myDonation)
        }
    }

    fun fetch(id: Int) {
        launch {
            val db = buildDb(getApplication())
            donationLD.value = db.donationDao().detailDonation(id)
        }
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://raw.githubusercontent.com/marianajunita17/json-anmp-uts/main/donation.json"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<ArrayList<Donation>>() { }.type
//                val result = Gson().fromJson<ArrayList<Donation>>(it, sType)
//
//                for (d in result){
//                    if (d.id == id){
//                        donationLD.value = d
//                        Log.d("loopdetail", d.id.toString())
//                    }
//                }
//                Log.d("involleydetail", result.toString())
//            },
//            {
//                Log.d("involleydetail", it.toString())
//            })
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }
}