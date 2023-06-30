package com.mariana.adv160420017uts.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Donation::class), version = 3)
abstract class DonationDatabase :RoomDatabase(){
    abstract fun donationDao(): DonationDao

    companion object{
        @Volatile private var instance: DonationDao ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DonationDatabase::class.java, "donasidb")
//                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build()

    }
}