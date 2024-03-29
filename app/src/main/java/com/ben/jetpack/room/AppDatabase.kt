package com.ben.jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        private var appDataBase: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (appDataBase == null) {
                synchronized(AppDatabase::class.java) {
                    if (appDataBase == null) {
                        appDataBase = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
                        return appDataBase!!
                    }
                }
            }
            return appDataBase!!
        }
    }
}