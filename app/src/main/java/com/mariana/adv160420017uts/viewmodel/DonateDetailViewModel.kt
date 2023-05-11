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

class DonateDetailViewModel(application: Application): AndroidViewModel(application) {
    val myDonationLD = MutableLiveData<MyDonation>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(id: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/marianajunita17/json-anmp-uts/main/donasiSaya.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<MyDonation>>() { }.type
                val result = Gson().fromJson<ArrayList<MyDonation>>(it, sType)

                for (myDonate in result){
                    if (myDonate.id == id){
                        myDonationLD.value = myDonate
                    }
                }

                Log.d("volleydonatedetail", result.toString())
            },
            {
                Log.d("volleydonatedetail", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}