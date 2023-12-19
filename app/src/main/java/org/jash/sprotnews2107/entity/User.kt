package org.jash.sprotnews2107.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity
data class User(
    val birthday: String?,
    val createTime: Date,
    val flag: Int,
    @PrimaryKey
    val id: Int,
    val imgurl: String?,
    val info: String?,
    val password: String?,
    val phone: String?,
    val sex: String?,
    val username: String?
)