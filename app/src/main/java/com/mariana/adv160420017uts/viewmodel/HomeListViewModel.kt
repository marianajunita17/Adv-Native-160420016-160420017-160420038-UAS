package com.mariana.adv160420017uts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mariana.adv160420017uts.model.Donation
import com.mariana.adv160420017uts.model.MyDonation
import com.mariana.adv160420017uts.util.donateDB
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

    var isRefreshing = false

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        donationLoadErrorLD.value = false
        loadingLD.value = true

        launch {
            donateDB(getApplication()).apply {
                donationsLD.postValue(this.donationDao().selectAllDonation())
            }
            loadingLD.postValue(false)
        }
    }

    fun insertDonation(myDonation: MyDonation) {
        launch {
            donateDB(getApplication()).apply {
                this.myDonationDao().donate(myDonation)
            }
        }
    }
}