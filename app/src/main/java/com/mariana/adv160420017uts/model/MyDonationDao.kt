package com.mariana.adv160420017uts.model

import androidx.room.*

@Dao
interface MyDonationDao {
    @Insert(onConflict =  OnConflictStrategy.ABORT)
    suspend fun donate(vararg myDonation: MyDonation)

    @Query("SELECT * FROM myDonation")
    fun selectAllMyDonation(): List<MyDonation>

    @Query("SELECT * FROM myDonation WHERE id= :id")
    fun detailMyDonation(id:Int): MyDonation
}