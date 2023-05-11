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

class HomeListViewModel(application: Application):AndroidViewModel(application) {
    val donationsLD = MutableLiveData<ArrayList<Donation>>()
    val donationLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    fun refresh() {
        donationLoadErrorLD.value = true
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/marianajunita17/json-anmp-uts/main/donation.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Donation>>() { }.type
                val result = Gson().fromJson<ArrayList<Donation>>(it, sType)

                donationsLD.value = result
                loadingLD.value = false
                donationLoadErrorLD.value = false

                Log.d("involley", result.toString())
            },
            {
                Log.d("involley", it.toString())
                donationLoadErrorLD.value = true
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}