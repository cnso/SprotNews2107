package org.jash.sprotnews2107.entity

import android.content.Context
import android.widget.Toast
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Category (
    @PrimaryKey
    val id: Int,
    val name: String,
    val info: String,
    val createTime: Date,
)