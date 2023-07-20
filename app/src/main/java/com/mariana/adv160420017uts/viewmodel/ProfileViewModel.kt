package com.mariana.adv160420017uts.viewmodel

import android.R
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mariana.adv160420017uts.model.User
import com.mariana.adv160420017uts.util.donateDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val profileLD = MutableLiveData<User>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun login(username: String, password: String) {
        launch{
            donateDB(getApplication()).apply {
                if ((username.isNotBlank() && password.isNotBlank())) {
                    profileLD.value = this.userDao().login(username, password)
                }
            }
        }
    }

    fun register(user: User, onSuccess: (success: Boolean) -> Unit) {
        launch {
            donateDB(getApplication()).apply {
                val id = this.userDao().register(user)
                if (id != 0.toLong()) {
                    profileLD.value = user
                    onSuccess(true)
                } else
                    onSuccess(false)
            }
        }
    }

    fun editProfile(dob: String, profesi: String, telp: String, username: String) {
        launch {
            donateDB(getApplication()).apply {
                val affected = this.userDao().editProfile(dob, profesi, telp, username)
                if (affected != 0) {
                    profileLD.value = this.userDao().profile(username)
                }
            }
        }
    }

    fun sessionLogin(username: String, saldo: Int) {

    }
}