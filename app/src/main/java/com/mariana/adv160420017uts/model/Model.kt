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
    val judul: String?,
    val photoUrl: String?,
    val tanggalDonasi: String?,
    val totalDonasi: String?
)