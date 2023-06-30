package com.mariana.adv160420017uts.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mariana.adv160420017uts.model.DonationDao
import com.mariana.adv160420017uts.model.DonationDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

val DB_NAME = "donasidb"

fun buildDb(context: Context):DonationDatabase {
//    val db = databaseBuilder(context,
//        DonationDao::class.java, DB_NAME)
//        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
//        .build()
//    return db
}

val MIGRATION_1_2 = object :Migration(1, 2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE donation ADD COLUMN "
        )
    }
}
fun ImageView.loadImage(url: String?, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(com.mariana.adv160420017uts.R.drawable.ic_baseline_error_24)
        .into(this, object :Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {

            }
        })
}