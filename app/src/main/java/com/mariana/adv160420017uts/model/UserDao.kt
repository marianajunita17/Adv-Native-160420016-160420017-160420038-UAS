package com.mariana.adv160420017uts.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun register(user: User): Long

    @Query("SELECT * FROM user WHERE username= :username AND password= :password")
    suspend fun login(username: String, password: String): User?

    @Query("UPDATE user SET password=:password, telp=:telp WHERE username=:username")
    suspend fun editProfile(password: String, telp: String, username: String): Int


}