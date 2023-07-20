package com.mariana.adv160420017uts.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room.databaseBuilder
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.model.DonationDatabase
import com.mariana.adv160420017uts.util.Util.Companion.DB_NAME
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class Util {
    companion object {
        const val DB_NAME = "donasidb"
    }
}

@BindingAdapter("android:imageUrl", "android:progressBar")
fun PhotoUrl(view: ImageView, url: String?, progressBar: ProgressBar) {
    view.loadImage(url, progressBar)
}

fun ImageView.loadImage(url: String?, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object :Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {

            }
        })
}

fun donateDB(context: Context): DonationDatabase {
    return databaseBuilder(
        context,
        DonationDatabase::class.java,
        DB_NAME).build()
}