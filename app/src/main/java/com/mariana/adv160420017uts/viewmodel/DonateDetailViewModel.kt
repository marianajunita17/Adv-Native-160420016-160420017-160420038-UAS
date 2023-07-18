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
import com.mariana.adv160420017uts.util.donateDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DonateDetailViewModel(application: Application):
    AndroidViewModel(application), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val myDonationLD = MutableLiveData<MyDonation>()

    fun fetch(id: Int) {
        launch {
            val db = donateDB(getApplication())
            myDonationLD.value = db.myDonationDao().detailMyDonation(id)
        }
    }

    fun displayMyDonate(id: Int) {
        launch {
            val db = donateDB(getApplication())
            myDonationLD.value = db.myDonationDao().detailMyDonation(id)
        }
    }
}