package com.mariana.adv160420017uts.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

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
    @ColumnInfo(name = "deskripsi")
    var deskripsi: String,
    @ColumnInfo(name = "hari")
    var hari: String,
    @ColumnInfo(name = "photo_url")
    var photoUrl: String
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}

@Entity
data class DonationHistory(
    @ColumnInfo(name = "donation_id")
    var donationId: String,
    @ColumnInfo(name = "total")
    var totalDonasi: String,
    @ColumnInfo(name = "date")
    var tanggalDonasi: String,
    @ColumnInfo(name = "user_username")
    var userUsername: String
) {
    @PrimaryKey(autoGenerate = true)
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