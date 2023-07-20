package com.mariana.adv160420017uts.model

import androidx.room.*

@Dao
interface DonationHistoryDao {
    @Insert(onConflict =  OnConflictStrategy.ABORT)
    suspend fun donate(donationHistory: DonationHistory)

    @Query("SELECT * FROM donationHistory")
    fun selectAllHistoryDonation(): List<DonationHistory>

    @Query("SELECT * FROM donationHistory WHERE id= :id")
    fun detailHistoryDonation(id:Int): DonationHistory

    @Query("SELECT * FROM DonationHistory WHERE user_username= :username")
    fun getDonationByUser(username: String): List<DonationHistory>
}