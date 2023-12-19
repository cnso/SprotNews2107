package org.jash.sprotnews2107.entity

import java.util.Date

data class Comment(
    var content: String?,
    val createTime: Date?,
    val id: Int,
    val nid: Int,
    var parentid: Int,
    var replays: List<Comment>?,
    val uid: Int
)