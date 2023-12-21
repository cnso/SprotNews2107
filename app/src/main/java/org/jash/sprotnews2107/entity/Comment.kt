package org.jash.sprotnews2107.entity

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
data class Comment(
    var content: String?,
    val createTime: Date?,
    val id: Int,
    val nid: Int,
    var parentid: Int,
    var replays: List<Comment>?,
    val uid: Int
) {
    lateinit var user:User
    val timeString:String
        get() {
            val time = createTime ?: Date()
            val l = (Date().time - time.time) / 1000
            return when {
                l < 60 -> "刚刚"
                l < 3600 -> "${l / 60}分钟之前"
                l < 3600 * 24 -> "${l / 3600}小时之前"
                l < 3600 * 48 -> "昨天"
                else -> sdf.format(time)
            }
        }
    val replayContent:CharSequence
        get() {
            val ssb = SpannableStringBuilder()
            ssb.append("${user.username}:", ForegroundColorSpan(Color.BLUE), Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            ssb.append(content, ForegroundColorSpan(Color.BLACK), Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            return ssb
        }
}