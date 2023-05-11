package com.mariana.adv160420017uts.viewmodel

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
import java.util.*
import kotlin.collections.ArrayList

class ProfileViewModel(application: Application): AndroidViewModel(application) {
    val profileLD = MutableLiveData<User>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch() {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/marianajunita17/json-anmp-uts/main/profileUser.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<User>>() { }.type
                val result = Gson().fromJson<ArrayList<User>>(it, sType)

                for(user in result){
                    if(user.id == "1"){
                        profileLD.value = user
                    }
                }
                Log.d("involleyprofile", result.toString())
            },
            {
                Log.d("involleydetail", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}