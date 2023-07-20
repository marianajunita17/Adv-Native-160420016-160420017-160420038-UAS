package com.mariana.adv160420017uts.model

import android.view.View
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mariana.adv160420017uts.view.ButtonDonateClickListener

@Entity
data class Donation(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "penggalang")
    var penggalang: String,
    @ColumnInfo(name = "terkumpul")
    var terkumpul: String,
    @ColumnInfo(name = "goals")
    var goals: String,
    @ColumnInfo(name = "donatur")
    var donatur: String,
    @ColumnInfo(name = "hari")
    var hari: String,
    @ColumnInfo(name = "photo_url")
    var photoUrl: String
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}

@Entity
data class MyDonation(
    @ColumnInfo(name = "status")
    var status: String,
    @ColumnInfo(name = "title")
    var judul: String,
    @ColumnInfo(name = "total")
    var totalDonasi: String,
    @ColumnInfo(name = "date")
    var tanggalDonasi: String,
    @ColumnInfo(name = "photo_url")
    var photoUrl: String
) {
    @PrimaryKey(autoGenerate = false)
    var id:Int = 0
}

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    var username: String,
    @ColumnInfo(name = "password")
    var password: String,
    @ColumnInfo(name = "gender")
    var gender: Int,
    @ColumnInfo(name = "dob")
    var dob: String,
    @ColumnInfo(name = "profesi")
    var profession: String,
    @ColumnInfo(name = "telp")
    var numberTelp: String,
    @ColumnInfo(name = "saldo")
    var saldo: Int,
    @ColumnInfo(name = "photo_url")
    var photoUrl: String
)

@Entity
data class Subscription(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "dana")
    var dana: String,
    @ColumnInfo("photo_url")
    var photoUrl: String
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}