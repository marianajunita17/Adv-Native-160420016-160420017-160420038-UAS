package com.mariana.adv160420017uts.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun register(user: User): Long

    @Query("SELECT * FROM user WHERE username= :username AND password= :password")
    suspend fun login(username: String, password: String): User

    @Query("SELECT * FROM user WHERE username= :username")
    suspend fun profile(username: String): User

    @Query("UPDATE user SET dob=:dob, profesi=:profesi, telp=:telp WHERE username=:username")
    suspend fun editProfile(dob: String, profesi: String, telp: String, username: String): Int
}