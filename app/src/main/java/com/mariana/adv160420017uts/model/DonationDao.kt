package com.mariana.adv160420017uts.model

import androidx.room.*

@Dao
interface DonationDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun addDonation(vararg donation:Donation)

    @Query("SELECT * FROM Donation")
    fun selectAllDonation(): List<Donation>

    @Query("SELECT * FROM donation WHERE id= :id")
    fun detailDonation(id:Int): Donation
}