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

class HomeDetailViewModel(application: Application): AndroidViewModel(application) {
    val donationLD = MutableLiveData<Donation>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(id: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/marianajunita17/json-anmp-uts/main/donation.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Donation>>() { }.type
                val result = Gson().fromJson<ArrayList<Donation>>(it, sType)

                for (d in result){
                    if (d.id == id){
                        donationLD.value = d
                        Log.d("loopdetail", d.id.toString())
                    }
                }
                Log.d("involleydetail", result.toString())
            },
            {
                Log.d("involleydetail", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}