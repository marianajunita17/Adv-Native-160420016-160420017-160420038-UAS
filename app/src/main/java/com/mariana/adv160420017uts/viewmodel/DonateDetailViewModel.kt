package com.mariana.adv160420017uts.viewmodel

import android.app.Application
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

class DonateDetailViewModel(application: Application):
    AndroidViewModel(application), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    val donationHistoryLD = MutableLiveData<DonationHistory>()
    val donationLD = MutableLiveData<Donation>()

    fun fetch(id: Int) {
        launch {
            buildDB(getApplication()).apply {
                donationHistoryLD.postValue(this.donationHistoryDao().detailHistoryDonation(id))
            }
        }
    }
}