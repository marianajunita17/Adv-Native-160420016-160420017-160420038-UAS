package com.mariana.adv160420017uts.util

import android.content.Context
import android.content.SharedPreferences
import com.mariana.adv160420017uts.model.User

private const val PREF_NAME = "SatuJiwa"
private const val PREF_IS_LOGIN = "PREF_IS_LOGIN"
private const val PREF_USERNAME = "PREF_USERNAME"
private const val PREF_USER = "PREF_USER"

class SharedPreferencesProvider(private val appContext: Context) {

    private val sharedPreferences: SharedPreferences
        get() = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getUsername(): String = sharedPreferences.getString(PREF_USERNAME, "").toString()

    fun getUser(): User = sharedPreferences.getString(PREF_USER, "").toString().toUser()

    fun isLogin(): Boolean = sharedPreferences.getBoolean(PREF_IS_LOGIN, false)

    fun sessionLogin(username: String, user: String){
        sharedPreferences.edit().apply {
            putString(PREF_USERNAME, username)
            putString(PREF_USER, user)
            putBoolean(PREF_IS_LOGIN, true)
        }.apply()
    }

    fun logout(){
        sharedPreferences.edit().clear().apply()
    }
}