package com.mariana.adv160420017uts.model

import com.google.gson.annotations.SerializedName

data class Donation(
    val id: String?,
    val title: String?,
    val penggalang: String?,
    val terkumpul: String?,
    val goals: String?,
    val donatur: String?,
    val hari: String?,
    @SerializedName("photo_url")
    val photoUrl: String?
)

data class MyDonation(
    val id: String?,
    val status: String?,
    @SerializedName("title")
    val judul: String?,
    @SerializedName("total")
    val totalDonasi: String?,
    @SerializedName("date")
    val tanggalDonasi: String?,
    @SerializedName("photo_url")
    val photoUrl: String?
)

data class User(
    val id: String?,
    @SerializedName("user_name")
    val username: String?,
    val gender: String?,
    val dob: String?,
    @SerializedName("profesi")
    val profession: String?,
    @SerializedName("telp")
    val numberTelp: String?,
    val saldo: String?,
    @SerializedName("photo_url")
    val photoUrl: String?
)

data class Subscription(
    val id: String?,
    val title: String?,
    val dana: String?,
    @SerializedName("photo_url")
    val photoUrl: String?
)