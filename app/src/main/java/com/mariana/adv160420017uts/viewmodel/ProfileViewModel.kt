package com.mariana.adv160420017uts.viewmodel

import android.R
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.mariana.adv160420017uts.model.User
import com.mariana.adv160420017uts.util.SharedPreferencesProvider
import com.mariana.adv160420017uts.util.donateDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val profileLD = MutableLiveData<User>()
    private val sharedPreferences = SharedPreferencesProvider(application.applicationContext)

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun login(username: String, password: String) {
        launch{
            donateDB(getApplication()).apply {
                if ((username.isNotBlank() && password.isNotBlank())) {
                    val user = this.userDao().login(username, password)
                    profileLD.postValue(user)

                    sessionLogin(user.username, Gson().toJson(user));
                }
            }
        }
    }

    fun isLogin() = sharedPreferences.isLogin()

    private fun sessionLogin(username: String, data: String) {
        sharedPreferences.sessionLogin(username, data)
    }

    fun getUserFromSharedPref() = sharedPreferences.getUser()

    fun logout() = sharedPreferences.logout()

    fun register(user: User, onSuccess: (success: Boolean) -> Unit) {
        launch {
            donateDB(getApplication()).apply {
                val id = this.userDao().register(user)
                if (id != 0.toLong()) {
                    profileLD.postValue(user)
                    sessionLogin(user.username, Gson().toJson(user))
                    onSuccess(true)
                } else
                    onSuccess(false)
            }
        }
    }

    fun editProfile(user: User, onSuccess: (success: Boolean) -> Unit) {
        launch {
            donateDB(getApplication()).apply {
                val affected = this.userDao().editProfile(user.dob, user.profession, user.numberTelp, user.username)
                if (affected != 0) {
                    profileLD.postValue(user)
                    sessionLogin(user.username, Gson().toJson(user))
                    onSuccess(true)
                } else {
                    onSuccess(false)
                }
            }
        }
    }
}