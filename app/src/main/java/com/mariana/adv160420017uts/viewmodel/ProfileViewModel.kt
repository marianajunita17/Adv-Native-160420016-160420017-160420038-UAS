package com.mariana.adv160420017uts.viewmodel

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

    fun login(username: String, password: String, onSuccess: (msg: String) -> Unit) {
        launch{
            donateDB(getApplication()).apply {
                if ((username.isNotBlank() && password.isNotBlank())) {
                    val user = this.userDao().login(username, password)
                    user?.let {
                        profileLD.postValue(it)
                        sessionLogin(user.username, Gson().toJson(user))
                        onSuccess("Login berhasil")
                    }

                    if (user == null)
                        onSuccess("Username atau password salah")
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

    fun register(user: User, onSuccess: (msg: String) -> Unit) {
        launch {
            donateDB(getApplication()).apply {
                val checkUser = this.userDao().login(user.username, user.password)
                checkUser?.let {
                    if (it.username == user.username) {
                        onSuccess("Username already taken")
                    }
                }

                if (checkUser == null) {
                    val id = this.userDao().register(user)
                    if (id != 0.toLong()) {
                        profileLD.postValue(user)
                        sessionLogin(user.username, Gson().toJson(user))
                        onSuccess("Register success")
                    } else
                        onSuccess("Register failed")
                }
            }
        }
    }

    fun editProfile(user: User, onSuccess: (msg: String) -> Unit) {
        launch {
            donateDB(getApplication()).apply {
                val affected = this.userDao().editProfile(user.dob, user.profession, user.numberTelp, user.username)
                if (affected != 0) {
                    profileLD.postValue(user)
                    sessionLogin(user.username, Gson().toJson(user))
                    onSuccess("Update data berhasil")
                } else {
                    onSuccess("Update data gagal")
                }
            }
        }
    }
}