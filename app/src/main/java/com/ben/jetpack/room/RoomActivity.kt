package com.ben.jetpack.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ben.jetpack.R
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.android.synthetic.main.room_user_item.view.*

class RoomActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        room_user_btn.setOnClickListener(this)
        room_age_btn.setOnClickListener(this)

        val users = AppDatabase.getInstance(this.applicationContext).getUserDao().getUsers()
        users.observe(this, Observer<List<User>> { list ->
            room_user_list.removeAllViews()
            list.forEach {
                val layout = LayoutInflater.from(this).inflate(R.layout.room_user_item, room_user_list, false)
                layout.room_item_name.text = it.name
                layout.room_item_age.text = it.age.toString()
                val root: ViewGroup = room_user_list
                root.addView(layout)
            }
        })
    }

    override fun onClick(v: View?) {
        when (v) {
            room_user_btn -> {
                addUser()
            }
            room_age_btn -> {
                addAge()
            }
            else -> {
            }
        }
    }

    private fun addAge() {
        Thread {
            val userDao = AppDatabase.getInstance(this.applicationContext).getUserDao()
            val users = userDao.getUsers()
            val list = if (users.value != null) users.value else mutableListOf()
            list!!.forEach {
                it.age = it.age + 1
            }
            userDao.insertUserList(list)
        }.start()
    }

    private fun addUser() {
        Thread {
            val user = User(System.currentTimeMillis().toInt(), "Jim", 1)
            AppDatabase.getInstance(this.applicationContext).getUserDao().insertUser(user)
        }.start()
    }
}
