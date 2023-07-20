package com.mariana.adv160420017uts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.mariana.adv160420017uts.model.DonationDatabase
import com.mariana.adv160420017uts.model.DonationHistory
import com.mariana.adv160420017uts.model.User
import com.mariana.adv160420017uts.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DonasiListViewModel(application: Application):
    AndroidViewModel(application), CoroutineScope {

    val donationsLDHistory = MutableLiveData<List<DonationHistory>>()
    val errorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    var isRefreshing = false

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(user: User) {
        loadingLD.value = true
        errorLD.value = false

        launch {
            buildDB(getApplication()).apply {
                donationsLDHistory.postValue(this.donationHistoryDao().getDonationByUser(user.username))
            }
        }
    }
}