package org.jash.sprotnews2107.entity

import android.content.Context
import android.widget.Toast
import java.util.Date

data class Category (
    val id: Int,
    val name: String,
    val info: String,
    val createTime: Date,
)