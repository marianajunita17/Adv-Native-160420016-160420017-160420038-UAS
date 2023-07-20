package com.mariana.adv160420017uts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.mariana.adv160420017uts.model.DonationDatabase
import com.mariana.adv160420017uts.model.MyDonation
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
    var isRefreshing = false

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
    }
}