package com.ben.jetpack.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE name = :name")
    fun getUser(name: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserList(list: List<User>)
}
