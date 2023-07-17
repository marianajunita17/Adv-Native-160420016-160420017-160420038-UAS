package com.mariana.adv160420017uts.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun register(vararg user:User)

    @Query("SELECT * FROM user WHERE username= :username AND password= :password")
    suspend fun login(username: String, password: String): User

    @Query("SELECT * FROM user WHERE username= :username")
    suspend fun profile(username: String, telp: String, saldo: String): User

    @Query("UPDATE user SET gender=:gender, dob=:dob, profesi=:profesi, telp=:telp WHERE username=:username")
    suspend fun editProfile(gender: String, dob: String, profesi: String, telp: String, username: String)

}