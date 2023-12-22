package org.jash.sprotnews2107.entity

data class Video(
    val createTime: String,
    val flag: Int,
    val id: Int,
    val imgurl: String,
    val info: String,
    val looks: Int,
    val suid: Int,
    val title: String,
    val type: Int,
    val videourl: String
)