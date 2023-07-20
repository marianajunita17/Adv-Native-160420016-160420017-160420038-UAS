package com.mariana.adv160420017uts.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mariana.adv160420017uts.util.Util
import com.mariana.adv160420017uts.util.buildDB

@Database(entities = arrayOf(Donation::class, User::class, DonationHistory::class, Subscription::class), version = 2)
abstract class DonationDatabase :RoomDatabase(){
    abstract fun donationDao(): DonationDao
    abstract fun donationHistoryDao(): DonationHistoryDao
    abstract fun userDao(): UserDao

    companion object{
        @Volatile private var instance: DonationDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            buildDB(context)

        operator fun invoke(context: Context) {
            if(instance != null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}