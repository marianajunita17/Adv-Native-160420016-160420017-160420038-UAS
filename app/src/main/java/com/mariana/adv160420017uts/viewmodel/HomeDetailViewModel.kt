package com.mariana.adv160420017uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mariana.adv160420017uts.model.Donation
import com.mariana.adv160420017uts.model.DonationHistory
import com.mariana.adv160420017uts.util.buildDB
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

    fun addDonate(donationHistory: DonationHistory){
        launch {
            buildDB(getApplication()).apply {
                this.donationHistoryDao().donate(donationHistory)
            }
        }
    }

    fun fetch(id: Int) {
        launch {
            buildDB(getApplication()).apply {
                val donation = this.donationDao().detailDonation(id)
                donation?.let {
                    donationLD.postValue(it)
                }

            }
        }
    }
}