package com.mariana.adv160420017uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.mariana.adv160420017uts.model.Donation
import com.mariana.adv160420017uts.model.DonationDatabase
import com.mariana.adv160420017uts.model.MyDonation
import com.mariana.adv160420017uts.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DonasiListViewModel(application: Application):
    AndroidViewModel(application), CoroutineScope {

    val myDonationsLD = MutableLiveData<List<MyDonation>>()
    val myDonationErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.value = true
        myDonationErrorLD.value = false

        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                DonationDatabase::class.java, "donasidb"
            ).build()

            myDonationsLD.postValue(db.myDonationDao().selectAllMyDonation())
        }
//
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://raw.githubusercontent.com/marianajunita17/json-anmp-uts/main/donasiSaya.json"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<ArrayList<MyDonation>>() { }.type
//                val result = Gson().fromJson<ArrayList<MyDonation>>(it, sType)
//
//                myDonationsLD.value = result
//                loadingLD.value = false
//
//                Log.d("involleymydonation", result.toString())
//            },
//            {
//                Log.d("involleymydonation", it.toString())
//                loadingLD.value = false
//            })
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }
}