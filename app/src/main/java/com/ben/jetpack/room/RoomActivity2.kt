package com.ben.jetpack.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ben.jetpack.R
import kotlinx.android.synthetic.main.activity_room2.*

class RoomActivity2 : AppCompatActivity() {
    private val names = listOf("Jame", "June", "Carry", "Tom", "Tim", "Jake", "Zim", "Fake", "Kris")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room2)

        button5.setOnClickListener {
            addUser()
        }
    }

    private fun addUser() {
        Thread {
            val user = User(System.currentTimeMillis().toInt(), names.random(), 1)
            AppDatabase.getInstance(this.applicationContext).getUserDao().insertUser(user)
        }.start()
    }
}