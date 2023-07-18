package com.mariana.adv160420017uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mariana.adv160420017uts.model.Donation
import com.mariana.adv160420017uts.model.DonationDatabase
import com.mariana.adv160420017uts.model.MyDonation
import com.mariana.adv160420017uts.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeListViewModel(application: Application)
    :AndroidViewModel(application), CoroutineScope {

    val donationsLD = MutableLiveData<List<Donation>>()
    val donationLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        donationLoadErrorLD.value = false
        loadingLD.value = true

        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                DonationDatabase::class.java, "donateDB"
            ).build()

            donationsLD.value = db.donationDao().selectAllDonation()
            loadingLD.value = false
        }
    }

    fun insertDonation(myDonation: MyDonation) {
        launch {
            val db = buildDb(getApplication())
            db.myDonationDao().donate(myDonation)
        }
    }
}