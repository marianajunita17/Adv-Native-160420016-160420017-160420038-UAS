package com.mariana.adv160420017uts.viewmodel

import android.R
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mariana.adv160420017uts.model.Donation
import com.mariana.adv160420017uts.model.User
import com.mariana.adv160420017uts.util.donateDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val profileLD = MutableLiveData<User>()
    var result = ""

    private var job = Job()

//    val TAG = "volleyTag"
//    private var queue: RequestQueue? = null

    fun login(username: String, password: String) {
        launch{
            val db = donateDB(getApplication())
            if (username != "" && password != "" || username != profileLD.value?.username && password != profileLD.value?.password) {
                result = "Gagal"
                Log.d("involleydetail", result.toString())
            } else {
                result = "Berhasil"
                Log.d("involleyprofile", result.toString())
            }
        }
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://raw.githubusercontent.com/marianajunita17/json-anmp-uts/main/profileUser.json"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<ArrayList<User>>() { }.type
//                val result = Gson().fromJson<ArrayList<User>>(it, sType)
//
//                for(user in result){
//                    if(user.id == "1"){
//                        profileLD.value = user
//                    }
//                }
//                Log.d("involleyprofile", result.toString())
//            },
//            {
//                Log.d("involleydetail", it.toString())
//            })
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }

    fun register(user: User) {
        launch {
            val db = donateDB(getApplication())
            db.userDao().register(user)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}