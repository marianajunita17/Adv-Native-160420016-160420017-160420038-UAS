package com.mariana.adv160420017uts.model

data class Donation(
    val id: String?,
    val title: String?,
    val penggalang: String?,
    val terkumpul: String?,
    val goals: String?,
    val donatur: String?,
    val hari: String?,
    val kisah: String?,
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