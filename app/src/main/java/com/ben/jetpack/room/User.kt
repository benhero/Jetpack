package com.ben.jetpack.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * com.ben.jetpack.room
 *
 * @author Benhero
 * @date   2019/10/31
 */
@Entity
data class User(
        @PrimaryKey(autoGenerate = true)
        val uid: Int,
        val name: String,
        var age: Int = 0
)