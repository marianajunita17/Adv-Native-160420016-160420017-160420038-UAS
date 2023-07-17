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
    val title: String?,
    @ColumnInfo(name = "penggalang")
    val penggalang: String?,
    @ColumnInfo(name = "terkumpul")
    val terkumpul: String?,
    @ColumnInfo(name = "goals")
    val goals: String?,
    @ColumnInfo(name = "donatur")
    val donatur: String?,
    @ColumnInfo(name = "hari")
    val hari: String?,
    @ColumnInfo(name = "photo_url")
    val photoUrl: String?
) : ButtonDonateClickListener {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    override fun onDonateClickListener(v: View) {
        TODO("Not yet implemented")
    }
}

data class MyDonation(
    @ColumnInfo(name = "status")
    val status: String?,
    @ColumnInfo(name = "title")
    val judul: String?,
    @ColumnInfo(name = "total")
    val totalDonasi: String?,
    @ColumnInfo(name = "date")
    val tanggalDonasi: String?,
    @ColumnInfo(name = "photo_url")
    val photoUrl: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}

data class User(
    @PrimaryKey(autoGenerate = false)
    val username: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "dob")
    val dob: String,
    @ColumnInfo(name = "profesi")
    val profession: String,
    @ColumnInfo(name = "telp")
    val numberTelp: String,
    @ColumnInfo(name = "saldo")
    val saldo: String,
    @ColumnInfo(name = "photo_url")
    val photoUrl: String
)

data class Subscription(
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "dana")
    val dana: String?,
    @ColumnInfo("photo_url")
    val photoUrl: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}